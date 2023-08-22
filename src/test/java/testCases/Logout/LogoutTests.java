package testCases.Logout;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Logout.Logout;
import pageObjects.MyAccount.MyAccount;
import testCases.BaseTest.BaseTest;


public class LogoutTests extends BaseTest {

    @Test
    public void testLogoutFromRightBar(){
       MyAccount account = landingPage.login();
       landingPage= account.clickOnLogoutLink().clickOnContinueButton();
        Assert.assertEquals(mDriver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    }

    @Test
    public void testLogoutFromMyAccount(){
    MyAccount account = landingPage.login();
    landingPage= account.clickOnLogoutFromMyAccountDropdown().clickOnContinueButton();
    Assert.assertEquals(mDriver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    }

    @Test
    public void testBackAfterLogout(){
        MyAccount account = landingPage.login();
        landingPage= account.clickOnLogoutFromMyAccountDropdown().clickOnContinueButton();
        mDriver.navigate().back();
        Logout logout = new Logout(mDriver);
        logout.clickOnMyAccountLink();
        Assert.assertEquals(mDriver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
    }

}
