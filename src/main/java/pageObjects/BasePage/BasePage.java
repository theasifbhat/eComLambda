package pageObjects.BasePage;

import lombok.Getter;
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
import pageObjects.Logout.Logout;
import pageObjects.MyAccount.MyAccount;
import utilities.GlobalFunctions;
import utilities.GlobalFunctions.*;

import java.time.Duration;
import java.util.List;

public class BasePage{

 WebDriver mDriver;
 WebDriverWait webDriverWait;


 //common webelements
 @FindBy(css = "div[id='entry_217821']> figure[class='figure']")
 private WebElement websiteLogo;

 @Getter            //getter
 @FindBy(xpath = "//ul[@class='navbar-nav horizontal']//i/parent::a")
 private WebElement myAccountNav;

 @Getter            //getter
 @FindBy(xpath = "//a[contains(@href, 'login')]")
 private WebElement loginLink;

 @Getter
 @FindBy(css = "a[href='https://ecommerce-playground.lambdatest.io/index.php?route=account/logout']")
 private WebElement logoutOptionFromMyAccountDropDown;


// Side bar elements in My Account page
 @FindBy(xpath = "//aside[@id='column-right']//a[@href='https://ecommerce-playground.lambdatest.io/index.php?route=account/logout']")
 WebElement logoutLink;

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

public MyAccount login(){
        Actions action = new Actions(mDriver);
        action.moveToElement(myAccountNav).build().perform();
        waitTillElementIsVisibleUsingWebElement(loginLink);
        action.click(loginLink).build().perform();
        Login login = new Login(mDriver);
        waitTillElementIsVisibleUsingWebElement(login.usernameField);
        return login.loginWithCredentials(GlobalFunctions.getPropertyFromPropertyFile("username"), GlobalFunctions.getPropertyFromPropertyFile("password"));
}


//sidebar events
public Logout clickOnLogoutLink(){
        waitTillElementIsVisibleUsingWebElement(logoutLink);
        logoutLink.click();
        return new Logout(mDriver);
}

//topbar events

public Logout clickOnLogoutFromMyAccountDropdown(){
    Actions actions = new Actions(mDriver);
    actions.moveToElement(getMyAccountNav()).build().perform();
    waitTillElementIsVisibleUsingWebElement(getLogoutOptionFromMyAccountDropDown());
    getLogoutOptionFromMyAccountDropDown().click();
    return new Logout(mDriver);
}


}
