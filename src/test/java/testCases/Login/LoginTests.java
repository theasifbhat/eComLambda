package testCases.Login;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.ForgotPassword.ForgotPassword;
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

@Test
public void testLoginWithIncorrectPassword(){
    Login login= landingPage.goToLoginPage();
    login.loginWithInvalidPassword();
    Assert.assertEquals("Warning: No match for E-Mail Address and/or Password.",login.getTopErrorMessageText());
}

@Test
    public void testLoginWithInvalidUsernameAndValidPassword(){
    Login login = landingPage.goToLoginPage();
    login.loginWithIncorrectUsernameAndValidPassword();
    Assert.assertEquals("Warning: No match for E-Mail Address and/or Password.",login.getTopErrorMessageText());
}

@Test
    public void testLoginWithInvalidCredentials(){
    Login login = landingPage.goToLoginPage();
    login.loginWithIncorrectUsernameAndPassword();
    Assert.assertEquals("Warning: No match for E-Mail Address and/or Password.",login.getTopErrorMessageText());
}

@Test
    public void testLoginWithEmptyFields(){
    Login login = landingPage.goToLoginPage();
    login.clickOnLoginButton();
    Assert.assertEquals("Warning: No match for E-Mail Address and/or Password.",login.getTopErrorMessageText());
}

@Test
    public void testForgotPasswordLink(){
    Login login = landingPage.goToLoginPage();
    Assert.assertEquals(login.getForgotPasswordLink(),"https://ecommerce-playground.lambdatest.io/index.php?route=account/forgotten");
    ForgotPassword forgotPassword = login.clickOnFogotPassword();
    Assert.assertEquals(forgotPassword.getForgotPasswordUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=account/forgotten");
}

}
