package pageObjects.BasePage;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import pageObjects.Wishlist.Wishlist;
import utilities.GlobalFunctions;

import java.time.Duration;
import java.util.List;

public class BasePage{

 WebDriver mDriver;
 WebDriverWait webDriverWait;


 //common webelements

//topbar elements
@FindBy(css = "div[id='entry_217821']> figure[class='figure']")
private WebElement websiteLogo;


@FindBy(css = "div[id='entry_217824']> a")
private WebElement topBarWishlistIcon;

@Getter
@FindBy(xpath = "//nav[@aria-label='breadcrumb']/following-sibling::div[contains(@class,'alert')]")
private WebElement topMessageContainer;


 // menu bar

 @Getter
 @FindBy(xpath = "//ul[@class='navbar-nav horizontal']//i/parent::a")
 private WebElement myAccountNav;

 @Getter
 @FindBy(xpath = "//a[contains(@href, 'login')]")
 private WebElement loginLink;

 @Getter
 @FindBy(css = "a[href='https://ecommerce-playground.lambdatest.io/index.php?route=account/logout']")
 private WebElement logoutOptionFromMyAccountDropDown;


// Side bar elements in My Account page
 @FindBy(xpath = "//aside[@id='column-right']//a[@href='https://ecommerce-playground.lambdatest.io/index.php?route=account/logout']")
 private WebElement logoutLink;

 @FindBy(xpath = "//aside[@id='column-right']//a[@href='https://ecommerce-playground.lambdatest.io/index.php?route=account/account']")
 @Getter
 private WebElement myAccountLink;

 @FindBy(xpath = "//ul[@class='navbar-nav horizontal']//i/parent::a/following-sibling::ul//span")
 @Getter
 private List<WebElement> optionsUnderMyAccount;

 //toast
 @Getter
 @FindBy(xpath = "//div[@class='toast-body']//p")
 private WebElement toastMessageContainer;

@FindBy(xpath = "//div[@id='notification-box-top']//button")
 private WebElement toastCloseButton;

public BasePage(WebDriver mDriver){
     this.mDriver= mDriver;
     webDriverWait = new WebDriverWait(mDriver, Duration.ofSeconds(13));
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

public void waitTillElementIsInvisible(WebElement webElement){
    webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(webElement));
}

public boolean isElementDisplayed(WebElement webElement){
    boolean flag = true;
    try {
        webElement.isDisplayed();
    }catch (NoSuchElementException e){
        flag = false;
    }
    return flag;
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

public MyAccount clickOnMyAccountLink(){
waitTillElementIsVisibleUsingWebElement(myAccountLink);
myAccountLink.click();
return new MyAccount(mDriver);      //will return Login if account if no account
}



//menubar events

public Logout clickOnLogoutFromMyAccountDropdown(){
    Actions actions = new Actions(mDriver);
    actions.moveToElement(getMyAccountNav()).build().perform();
    waitTillElementIsVisibleUsingWebElement(getLogoutOptionFromMyAccountDropDown());
    getLogoutOptionFromMyAccountDropDown().click();
    return new Logout(mDriver);
}


//topbar events

    public Wishlist clickOnTopBarWishlistIcon(){
//    Actions actions = new Actions(mDriver);
//    actions.moveToElement(topBarWishlistIcon).click().build().perform();
        topBarWishlistIcon.click();
    return new Wishlist(mDriver);
    }


//toast messages

 public String getSanitizedToastText(){
    waitTillElementIsVisibleUsingWebElement(toastMessageContainer);
    return toastMessageContainer.getText().replaceAll("[\\t\\n\\r]+"," ");
 }

//topbar messages
public String getTopbarMessage(){
    waitTillElementIsVisibleUsingWebElement(topMessageContainer);
    return toastMessageContainer.getText();
}

public void closeToastBox(){
    toastCloseButton.click();
}



}
