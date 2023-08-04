package testCases.Register;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Login.Login;
import pageObjects.Register.Register;
import testCases.BaseTest.BaseTest;

public class RegisterTests extends BaseTest {

    @Test
    public void testRegisterAccountWithMandatoryFields(){
        Register register = landingPage.goToRegisterPage();
        register.fillMandatoryFields();
        register.clickContinue();
        if (!(register.checkPageUrlAfterSuccessfulRegister()&& register.getAccountRegisterSuccessMessage())){
            Assert.fail("Error after registering account");
        }
    }


    @Test
    public void testRegisterAccountWithAllFieldsWithNewsletterNo(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithNewsLetterSetToNo();
        register.clickContinue();
        if (!(register.checkPageUrlAfterSuccessfulRegister()&& register.getAccountRegisterSuccessMessage())){
            Assert.fail("Error after registering account with full details and newsletter set to no");
        }

    }

    @Test
    public void testRegisterAccountWithAllFieldsWithNewsletterYes(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithNewsLetterSetToYes();
        register.clickContinue();
        if (!(register.checkPageUrlAfterSuccessfulRegister()&& register.getAccountRegisterSuccessMessage())){
            Assert.fail("Error after registering account with full details and newsletter set to yes");
        }
    }

    @Test
    public void testErrorMessagesForFields(){
        Register register = landingPage.goToRegisterPage();
        register.clickContinue();
        if(!(register.checkFirstNameErrorMessage() &&
                register.checkLastNameErrorMessage() &&
                register.checkEmailErrorMessage() &&
                register.checkPasswordErrorMessage() &&
                register.checkTelephoneErrorMessage() &&
                register.checkWarningYouMustAgree())){
            Assert.fail("Error messages are not displayed for mandatory fields");
        }
    }


    @Test
    public void testRegisterPageWithDifferentRoute(){
        Login login = landingPage.goToLoginPage();
        Register register = login.clickOnContinueButton();
        if(!register.isCurrentPageRegisterPage()){
            Assert.fail("Register page is not displayed");
        }
    }

    @Test
    public void testRegisterUserWithDifferentPasswords(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithIncorrectPassword();
        register.clickContinue();
        if (!register.checkConfirmPasswordDoesNotMatch()){
            Assert.fail("Error message is not displayed when password and confirm password do not match");
        }
    }

    @Test
    public void testRegisterUserWithExistingEmail(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithExistingEmail();
        register.clickContinue();
        if (!register.checkAccountExistWarning()){
            Assert.fail("Error message is not displayed when email already exists");
        }
    }

}
