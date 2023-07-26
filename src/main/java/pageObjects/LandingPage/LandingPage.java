package pageObjects.LandingPage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


}
