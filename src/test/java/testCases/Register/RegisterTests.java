package testCases.Register;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Login.Login;
import pageObjects.Register.Register;
import testCases.BaseTest.BaseTest;

public class RegisterTests extends BaseTest {

    @Test
    public void testRegisterAccountWithMandatoryFields(){
        Register register = landingPage.goToRegisterPage();
        register.fillMandatoryFields();
        register.clickContinue();
        Assert.assertEquals(register.getAccountRegisterSuccessMessage(), "Your Account Has Been Created!");
        if (!(register.checkPageUrlAfterSuccessfulRegister())){
            Assert.fail("Error after registering account");
        }
    }


    @Test
    public void testRegisterAccountWithAllFieldsWithNewsletterNo(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithNewsLetterSetToNo();
        register.clickContinue();
        Assert.assertEquals(register.getAccountRegisterSuccessMessage(), "Your Account Has Been Created!");
        if (!(register.checkPageUrlAfterSuccessfulRegister())){
            Assert.fail("Error after registering account");
        }

    }

    @Test
    public void testRegisterAccountWithAllFieldsWithNewsletterYes(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithNewsLetterSetToYes();
        register.clickContinue();
        Assert.assertEquals(register.getAccountRegisterSuccessMessage(), "Your Account Has Been Created!");
        if (!(register.checkPageUrlAfterSuccessfulRegister())){
            Assert.fail("Error after registering account");
        }
    }

    @Test
    public void testErrorMessagesForFields(){
        Register register = landingPage.goToRegisterPage();
        register.clickContinue();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(register.getFirstNameErrorMessage(), "First Name must be between 1 and 32 characters!");
        softAssert.assertEquals(register.getLastNameErrorMessage(), "Last Name must be between 1 and 32 characters!");
        softAssert.assertEquals(register.getPasswordErrorMessage(), "Password must be between 4 and 20 characters!");
        softAssert.assertEquals(register.getEmailErrorMessage(), "E-Mail Address does not appear to be valid!");
        softAssert.assertEquals(register.getTelephoneErrorMessage(), "Telephone must be between 3 and 32 characters!");
        softAssert.assertEquals(register.getTopErrorText(), "Warning: You must agree to the Privacy Policy!");
        softAssert.assertAll();
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
        Assert.assertEquals(register.getConfirmPasswordDoesNotMatchText(),"Password confirmation does not match password!");
    }

    @Test
    public void testRegisterUserWithExistingEmail(){
        Register register = landingPage.goToRegisterPage();
        register.fillAllFieldsWithExistingEmail();
        register.clickContinue();
        Assert.assertEquals(register.getTopErrorText(),"Error message is not displayed when email already exists");
    }

    @Test
    public void testIncorrectPhoneNumber(){
        Register register = landingPage.goToRegisterPage();
        register.fillMandatoryFields();
        register.setTelephoneInputBoxText("12");
        register.clickContinue();
        Assert.assertEquals(register.getTelephoneErrorMessage(), "Telephone must be between 3 and 32 characters!");
    }

}
