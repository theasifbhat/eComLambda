package testCases.Checkout;

import com.beust.ah.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Checkout.Checkout;
import pageObjects.ConfirmOrder.ConfirmOrder;
import pageObjects.OrderSuccess.OrderSuccess;
import pageObjects.Search.Search;
import pageObjects.ShoppingCart.ShoppingCart;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

public class CheckoutTests extends BaseTest {


@Test
public void testCheckoutWithSignedInUserInCheckoutPage(){
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem = search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getShoppingCartLinkInToast());
    search.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);
    shoppingCart.getCheckoutButton().click();
    Checkout checkout = new Checkout(mDriver);
    actions.moveToElement(checkout.getAccountLoginRadio()).click().build().perform();
    checkout.waitTillElementIsVisibleUsingWebElement(checkout.getLoginEmailInput());
    checkout.getLoginEmailInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("username"));
    checkout.getLoginPasswordInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("password"));
    checkout.getLoginButton().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    //checkout.waitTillElementIsVisibleUsingWebElement(checkout.getExistingAddressRadio());
    //need to check why timeout exception is thrown
    actions.moveToElement(checkout.getExistingAddressRadio()).click().build().perform();

    checkout.waitTillElementIsEnabledUsingWebElement(checkout.getExistingAddressDropdown());

    Select select = new Select(checkout.getExistingAddressDropdown());

    SoftAssert sf = new SoftAssert();

    sf.assertEquals(select.getFirstSelectedOption().getText(),"firstname lastname, address1, city, Jammu and Kashmir, India");
    sf.assertTrue(checkout.getCodRadio().isSelected());
    sf.assertTrue(checkout.getShippingMethodRadio().isSelected());

    actions.moveToElement(checkout.getTosCheckbox()).click().build().perform();
    actions.moveToElement(checkout.getContinueButton()).click().build().perform();
    ConfirmOrder confirmOrder= checkout.clickContinueButton();
    OrderSuccess os = confirmOrder.clickConfirmOrderButton();
    sf.assertEquals(os.getOrderSuccessLabel().getText(),"Your order has been placed!");
    sf.assertAll();
}


@Test
    public void checkoutWithNewAddressAndMandatoryFields() throws InterruptedException {
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem = search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getShoppingCartLinkInToast());
    search.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);
    shoppingCart.getCheckoutButton().click();
    Checkout checkout = new Checkout(mDriver);
    actions.moveToElement(checkout.getAccountLoginRadio()).click().build().perform();
    checkout.waitTillElementIsVisibleUsingWebElement(checkout.getLoginEmailInput());
    checkout.getLoginEmailInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("username"));
    checkout.getLoginPasswordInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("password"));
    checkout.getLoginButton().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    checkout.fillMandatoryFieldsInBillingSectionWithRandomData();
    actions.moveToElement(checkout.getContinueButton()).click().build().perform();
    ConfirmOrder confirmOrder= checkout.clickContinueButton();
    OrderSuccess os = confirmOrder.clickConfirmOrderButton();
    Assert.assertEquals(os.getOrderSuccessLabel().getText(),"Your order has been placed!");
}


@Test
    public void testCheckoutWihNewAddressWithAllFields() throws InterruptedException {
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem = search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getShoppingCartLinkInToast());
    search.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);
    shoppingCart.getCheckoutButton().click();
    Checkout checkout = new Checkout(mDriver);
    actions.moveToElement(checkout.getAccountLoginRadio()).click().build().perform();
    checkout.waitTillElementIsVisibleUsingWebElement(checkout.getLoginEmailInput());
    checkout.getLoginEmailInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("username"));
    checkout.getLoginPasswordInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("password"));
    checkout.getLoginButton().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    checkout.fillNonMandatoryFieldsInBillingSectionWithRandomData();
    checkout.fillMandatoryFieldsInBillingSectionWithRandomData();
    actions.moveToElement(checkout.getContinueButton()).click().build().perform();
    ConfirmOrder confirmOrder= checkout.clickContinueButton();
    OrderSuccess os = confirmOrder.clickConfirmOrderButton();
    Assert.assertEquals(os.getOrderSuccessLabel().getText(),"Your order has been placed!");
}


@Test
public void testBillingSectionHasPlaceholder(){
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem = search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getShoppingCartLinkInToast());
    search.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);
    shoppingCart.getCheckoutButton().click();
    Checkout checkout = new Checkout(mDriver);
    actions.moveToElement(checkout.getAccountLoginRadio()).click().build().perform();
    checkout.waitTillElementIsVisibleUsingWebElement(checkout.getLoginEmailInput());
    checkout.getLoginEmailInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("username"));
    checkout.getLoginPasswordInput().sendKeys(GlobalFunctions.getPropertyFromPropertyFile("password"));
    checkout.getLoginButton().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getFirstNameInput(), "placeholder"),"First Name");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getLastNameInput(),"placeholder"),"Last Name");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getCompanyInput(),"placeholder"),"Company");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getAddress1Input(),"placeholder"),"Address 1");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getAddress2Input(),"placeholder"),"Address 2");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getCityInput(),"placeholder"),"City");
    softAssert.assertEquals(checkout.getAttributrOfWebElement(checkout.getPostcodeInput(),"placeholder"),"Post Code");
    softAssert.assertAll();
}




}
