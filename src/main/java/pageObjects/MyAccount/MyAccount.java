package pageObjects.MyAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

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

    public String getMyAccountLabelText(){
        return myAccountLabel.getText();
    }

    public String getMyOrdersLabelTest(){
        return myOrdersLabel.getText();
    }

    public String getMyAffiliateAccountLabelText(){
        return  myAffiliateAccountLabel.getText();
    }


}
