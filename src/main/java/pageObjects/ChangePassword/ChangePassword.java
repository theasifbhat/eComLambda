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

    @FindBy(xpath = "//nav[@aria-label='breadcrumb']/following-sibling::div[contains(@class,'danger')]")
    WebElement topMessage;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(id = "input-confirm")
    WebElement confirmPassword;



    public String getTopMessage() {
        waitTillElementIsVisibleUsingWebElement(topMessage);
        return topMessage.getText();
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
