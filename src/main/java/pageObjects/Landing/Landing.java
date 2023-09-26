package pageObjects.Landing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;
import pageObjects.Login.Login;
import pageObjects.Product.Product;
import pageObjects.Register.Register;
import pageObjects.Search.Search;
import utilities.GlobalFunctions;

import java.util.List;

public class Landing extends BasePage {
    WebDriver mDriver;

    public Landing(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(xpath = "//div[@class='product-thumb']")
    List<WebElement> searchResults;

    @FindBy(xpath = "//a[contains(@href, 'register')]")
    WebElement registerLink;


    public void goToLandingPage() {
        mDriver.get("https://"+
                GlobalFunctions.getPropertyFromPropertyFile("url"));   //hardcoded https because updateproperties messes up with the url
    }
    public Register goToRegisterPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(getMyAccountNav()).build().perform();
        waitTillElementIsVisibleUsingWebElement(registerLink);
        actions.click(registerLink).build().perform();
        return new Register(mDriver);
    }

    public Login goToLoginPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(getMyAccountNav()).build().perform();
        waitTillElementIsVisibleUsingWebElement(getLoginLink());
        actions.click(getLoginLink()).build().perform();
        return new Login(mDriver);
    }




}
