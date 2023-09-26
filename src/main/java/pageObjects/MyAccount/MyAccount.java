package pageObjects.MyAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.ChangePassword.ChangePassword;
import pageObjects.Wishlist.Wishlist;

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

    @FindBy(xpath = "//i[@class='fas fa-heart fa-fw mr-1']/parent::a")
    private WebElement myWishlist;

    public String getMyAccountLabelText(){
        return myAccountLabel.getText();
    }

    public String getMyOrdersLabelTest(){
        return myOrdersLabel.getText();
    }

    public String getMyAffiliateAccountLabelText(){
        return  myAffiliateAccountLabel.getText();
    }

    public ChangePassword clickOnChangePasswordOption(){
        changePasswordOption.click();
        return new ChangePassword(mDriver);
    }

    public Wishlist clickOnMyWishlist(){
        new Actions(mDriver).moveToElement(myWishlist).click().build().perform();
      //  myWishlist.click();
        return new Wishlist(mDriver);
    }


}
