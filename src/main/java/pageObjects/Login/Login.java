package pageObjects.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.Register.Register;

public class Login extends BasePage {

    WebDriver mDriver;
    @FindBy(id = "input-email")
    public WebElement usernameField;   // made public to add wait in different pages while logging in

    @FindBy(id = "input-password")
    WebElement passwordField;

    @FindBy(css = "input[value='Login']")
    WebElement loginButton;

    @FindBy(css ="a[class$='btn-primary']")
    WebElement continueButton;


    public Login(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }

    public void loginWithValidCredentials(String username, String password){
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        goToHomepage();
    }

    public Register clickOnContinueButton(){
        waitTillElementIsVisibleUsingWebElement(continueButton);
        continueButton.click();
        return new Register(mDriver);
    }


}
