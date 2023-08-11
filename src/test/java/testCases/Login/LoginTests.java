package testCases.Login;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Login.Login;
import pageObjects.MyAccount.MyAccount;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

public class LoginTests extends BaseTest {

@Test
public void testLoginWithValidCredentials(){
   Login login =  landingPage.goToLoginPage();
   MyAccount myAccount = login.loginWithValidCredentials(GlobalFunctions.getPropertyFromPropertyFileWithKey("username"),GlobalFunctions.getPropertyFromPropertyFileWithKey("password"));
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(myAccount.getMyAccountLabelText(),"My Account");
    softAssert.assertEquals(myAccount.getMyOrdersLabelTest(),"My Orders");
    softAssert.assertEquals(myAccount.getMyAffiliateAccountLabelText(),"My Affiliate Account");
    softAssert.assertAll();
}



}
