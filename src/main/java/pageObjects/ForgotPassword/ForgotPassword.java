package pageObjects.ForgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.Login.Login;

public class ForgotPassword extends BasePage {
    WebDriver mDriver;

    public ForgotPassword(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(css = "div[id='content'] > h1")
    private WebElement forgotPasswordLabel;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(css = "button[class*='btn-primary']")
    private WebElement continueButton;

    @FindBy(css = "a[class='btn btn-secondary']")
    private WebElement backButton;


    public void sendTextToEmailField(String text){
        emailField.sendKeys(text);
    }

    public String getForgotPasswordLabelText(){
        return forgotPasswordLabel.getText();
    }

    public Login clickOnBackButton(){
        backButton.click();
        return new Login(mDriver);
    }


}
