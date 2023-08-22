package testCases.Logout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @Test
    public void testLogoutDropdownNotAvailableWhenUserNotLoggedIn(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(landingPage.getMyAccountNav()).build().perform();
        landingPage.waitTillElementsAreVisibleUsingWebElement(landingPage.getOptionsUnderMyAccount());
        for (WebElement item : landingPage.getOptionsUnderMyAccount()){
           if (item.getText().equalsIgnoreCase("logout")){
               Assert.fail("Logout Found");
           }

        }
    }

}
