package pageObjects.ChangePassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import utilities.GlobalFunctions;

public class ChangePassword extends BasePage {
    WebDriver mDriver;
    public ChangePassword(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }


    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(id = "input-confirm")
    private WebElement confirmPassword;

    @FindBy(css = "input[value='Continue']")
    private WebElement continueButton;


    public void clickOnContinueButton(){
        waitTillElementIsVisibleUsingWebElement(continueButton);
        continueButton.click();
    }

    public String sendNewPasswordToFields(){
        waitTillElementIsVisibleUsingWebElement(password);
        waitTillElementIsVisibleUsingWebElement(confirmPassword);
        String newPassword = GlobalFunctions.generateRandomString();
        password.sendKeys(newPassword);
        confirmPassword.sendKeys(newPassword);
        return newPassword;
    }



}
