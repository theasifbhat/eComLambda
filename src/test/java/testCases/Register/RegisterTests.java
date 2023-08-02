package testCases.Register;

import org.testng.annotations.Test;
import pageObjects.Register.Register;
import testCases.BaseTest.BaseTest;

public class RegisterTests extends BaseTest {


    @Test
    public void testRegisterAccountWithMandatoryFields(){
        Register register = landingPage.goToRegisterPage();
        waitForPageLoaded();
        register.fillMandatoryFields();

        ///further code to be completed

    }
}
