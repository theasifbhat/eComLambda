package testCases.Logout;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Landing.Landing;
import pageObjects.Login.Login;
import pageObjects.Logout.Logout;
import pageObjects.MyAccount.MyAccount;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

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

}
