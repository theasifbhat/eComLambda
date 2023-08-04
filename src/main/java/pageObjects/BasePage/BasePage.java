package pageObjects.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Landing.Landing;
import pageObjects.Login.Login;
import utilities.GlobalFunctions;

import java.time.Duration;
import java.util.List;

public class BasePage{

 WebDriver mDriver;
 WebDriverWait webDriverWait;


 //common webelements
 @FindBy(css = "div[id='entry_217821']> figure[class='figure']")
 public WebElement websiteLogo;

 @FindBy(xpath = "//ul[@class='navbar-nav horizontal']//i/parent::a")
 public WebElement myAccountNav;

 @FindBy(xpath = "//a[contains(@href, 'login')]")
 public WebElement loginLink;





 public BasePage(WebDriver mDriver){
     this.mDriver= mDriver;
     webDriverWait = new WebDriverWait(mDriver, Duration.ofSeconds(7));
     PageFactory.initElements(mDriver,this);
     GlobalFunctions.waitForPageLoad(mDriver);
 }


 public Landing goToHomepage(){
     waitTillElementIsVisibleUsingWebElement(websiteLogo);
     mDriver.get("https://ecommerce-playground.lambdatest.io/");
     return new Landing(mDriver);
 }

 public void waitTillElementIsVisible(By locator){
     webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }

    public void waitTillElementIsVisibleUsingWebElement(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitTillElementsAreVisibleUsingWebElement(List<WebElement> element){
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitTillElementIsPresent(By locator){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public Landing login(){
        Actions action = new Actions(mDriver);
        action.moveToElement(myAccountNav).build().perform();
        waitTillElementIsVisibleUsingWebElement(loginLink);
        action.click(loginLink).build().perform();
        Login login = new Login(mDriver);
        waitTillElementIsVisibleUsingWebElement(login.usernameField);
        return login.loginWithValidCredentials(GlobalFunctions.getPropertyFromPropertyFileWithKey("username"), GlobalFunctions.getPropertyFromPropertyFileWithKey("password"));
    }


}
