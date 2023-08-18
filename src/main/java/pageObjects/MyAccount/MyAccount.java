package pageObjects.MyAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.ChangePassword.ChangePassword;

public class MyAccount extends BasePage {
    WebDriver mDriver;
    public MyAccount(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(xpath = "(//h2[contains(@class,'h5')])[1]")
    WebElement myAccountLabel;

    @FindBy(xpath = "(//h2[contains(@class,'h5')])[2]")
    WebElement myOrdersLabel;

    @FindBy(xpath = "(//h2[contains(@class,'h5')])[3]")
    WebElement myAffiliateAccountLabel;

    @FindBy(css = "a[href='https://ecommerce-playground.lambdatest.io/index.php?route=account/password']")
    WebElement changePasswordOption;
    @FindBy(xpath = "//nav[@aria-label='breadcrumb']/following-sibling::div[contains(@class,'alert')]")
    private WebElement topMessage;

    public String getMyAccountLabelText(){
        return myAccountLabel.getText();
    }

    public String getMyOrdersLabelTest(){
        return myOrdersLabel.getText();
    }

    public String getMyAffiliateAccountLabelText(){
        return  myAffiliateAccountLabel.getText();
    }
    public String getTopMessage() {
        waitTillElementIsVisibleUsingWebElement(topMessage);
        return topMessage.getText();
    }

    public ChangePassword clickOnChangePasswordOption(){
        changePasswordOption.click();
        return new ChangePassword(mDriver);
    }


}
