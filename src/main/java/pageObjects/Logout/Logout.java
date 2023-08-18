package pageObjects.Logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.Landing.Landing;

public class Logout extends BasePage {
    WebDriver mDriver;
    public Logout(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(linkText = "Continue")
    WebElement continueButton;


    public Landing clickOnContinueButton(){
        waitTillElementIsVisibleUsingWebElement(continueButton);
        continueButton.click();
        return new Landing(mDriver);
    }

}
