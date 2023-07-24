package pageObjects.LandingPage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;

import java.util.List;

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
    List<WebElement> searchResults;




    public void goToLandingPage() {
        mDriver.get("https://ecommerce-playground.lambdatest.io/");
    }

    public void searchProductWithName(String text){
        searchBar.clear();
        searchBar.sendKeys(text);
        submitButton.click();
    }

    public void getSearchSuggestionWithName(String text){
        searchBar.clear();
        searchBar.sendKeys(text);
        waitTillElementsAreVisibleUsingWebElement(searchResults);
        searchResults.forEach(item->{

        if (!item.getText().toLowerCase().contains(text.toLowerCase())){
            Assert.fail("Result with mismatching name found.");
        }
                }
        );
    }


}
