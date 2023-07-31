package pageObjects.LandingPage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;
import pageObjects.Login.Login;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class LandingPage extends BasePage {

    WebDriver mDriver;
    public LandingPage(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(xpath = "(//div[@id='search']//input[@name='search'])[1]")
    WebElement searchBar;

    @FindBy(xpath = "//div[@class='search-wrapper']//button[@class='type-text']")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='dropdown']//h4[@class='title']")
    List<WebElement> searchSuggestionResults;

    @FindBy(css = "div[id='entry_212469']")
    WebElement searchResultContainer;

    @FindBy(xpath = "//div[@class='product-thumb']")
    List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    List<WebElement> searchResultItemName;

    @FindBy(xpath = "//div[@id='entry_212469']//p")
    WebElement noResultText;

    @FindBy(xpath = "//ul[@class='navbar-nav horizontal']//i/parent::a")
    WebElement myAccountNav;

    @FindBy(xpath = "//a[contains(@href, 'login')]")
    WebElement loginLink;



    public void goToLandingPage() {
        mDriver.get("https://ecommerce-playground.lambdatest.io/");
    }

    public void searchProductWithName(String text){
        searchBar.clear();
        searchBar.sendKeys(text);
        submitButton.click();
    }

    public boolean searchWithEmptyProductName(){
        searchBar.clear();
        submitButton.click();
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        return mDriver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search=");

    }

    public void getSearchSuggestionWithName(String text){
        searchBar.clear();
        searchBar.sendKeys(text);
        waitTillElementsAreVisibleUsingWebElement(searchSuggestionResults);
        searchSuggestionResults.forEach(item->{
        if (!item.getText().toLowerCase().contains(text.toLowerCase())){
            Assert.fail("Result with mismatching name found.");
        }});
    }

    public List<String> getSearchResultNames(){
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        List<String> items = new ArrayList<>();
        searchResultItemName.forEach(it->{
            items.add(it.getText());
        });
        return items;
    }

    public boolean searchProductsWithNonExistingProductName(){   //returns true if label is found else false
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        return Objects.equals(noResultText.getText(), "There is no product that matches the search criteria.");
    }

    public void login(){
        Actions action = new Actions(mDriver);
        action.moveToElement(myAccountNav).build().perform();
        waitTillElementIsVisibleUsingWebElement(loginLink);
        action.click(loginLink).build().perform();
        Login login = new Login(mDriver);
        waitTillElementIsVisibleUsingWebElement(login.usernameField);
        try {
            Properties properties = new Properties();
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
            properties.load(fs);
            login.loginWithValidCredentials(properties.getProperty("username"), properties.getProperty("password"));
        }catch (Exception ignored){
            System.out.println("Cannot find username and password from properties file.");
        }

    }


}
