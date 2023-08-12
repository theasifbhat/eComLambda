package pageObjects.Login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.ForgotPassword.ForgotPassword;
import pageObjects.Landing.Landing;
import pageObjects.MyAccount.MyAccount;
import pageObjects.Register.Register;
import utilities.GlobalFunctions;

public class Login extends BasePage {

    WebDriver mDriver;
    @FindBy(id = "input-email")
    public WebElement usernameField;   // made public to add wait in different pages while logging in

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(css = "input[value='Login']")
    private WebElement loginButton;

    @FindBy(css ="a[class$='btn-primary']")
    private WebElement continueButton;

    @FindBy(css = "div[class='alert alert-danger alert-dismissible']")
    private WebElement topErrorMessage;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::a")
    private WebElement forgotPassword;


    public Login(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }


    public Register clickOnContinueButton(){
        waitTillElementIsVisibleUsingWebElement(continueButton);
        continueButton.click();
        return new Register(mDriver);
    }

    public void clickOnLoginButton(){
        waitTillElementIsVisibleUsingWebElement(loginButton);
        loginButton.click();
    }

    public String getTopErrorMessageText(){
        waitTillElementIsVisibleUsingWebElement(topErrorMessage);
        return topErrorMessage.getText();
    }

    public String getForgotPasswordLink(){
       return forgotPassword.getAttribute("href");
    }

    public ForgotPassword clickOnFogotPassword(){
        forgotPassword.click();
        return new ForgotPassword(mDriver);
    }

    public MyAccount loginWithValidCredentials(String username, String password){
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        return new MyAccount(mDriver);
    }

    public void loginWithInvalidPassword(){
        usernameField.clear();
        usernameField.sendKeys(GlobalFunctions.getPropertyFromPropertyFileWithKey("username"));
        passwordField.clear();
        passwordField.sendKeys("123445");
        loginButton.click();
    }

    public void loginWithIncorrectUsernameAndPassword(){
        usernameField.sendKeys("asif@lamdatest.com");
        passwordField.sendKeys("12334");
        loginButton.click();
    }

    public void loginWithIncorrectUsernameAndValidPassword(){
        usernameField.sendKeys("asif@lamdatest.com");
        passwordField.sendKeys(GlobalFunctions.getPropertyFromPropertyFileWithKey("password"));
        loginButton.click();
    }


}
