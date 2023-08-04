package pageObjects.Landing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;
import pageObjects.Login.Login;
import pageObjects.Register.Register;

import java.util.List;

public class Landing extends BasePage {
    WebDriver mDriver;

    public Landing(WebDriver mDriver) {
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
    @FindBy(xpath = "//div[@class='product-thumb']")
    List<WebElement> searchResults;

    @FindBy(xpath = "//a[contains(@href, 'register')]")
    WebElement registerLink;

    public void searchProductWithName(String text){
        searchBar.clear();
        searchBar.sendKeys(text);
        submitButton.click();
    }
    public void goToLandingPage() {
        mDriver.get("https://ecommerce-playground.lambdatest.io/");
    }
    public Register goToRegisterPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(myAccountNav).build().perform();
        waitTillElementIsVisibleUsingWebElement(registerLink);
        actions.click(registerLink).build().perform();
        return new Register(mDriver);
    }

    public Login goToLoginPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(myAccountNav).build().perform();
        waitTillElementIsVisibleUsingWebElement(loginLink);
        actions.click(loginLink).build().perform();
        return new Login(mDriver);
    }
        public void clickOnSearchButtonWithoutText(){
        searchBar.clear();
        submitButton.click();
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

}
