package testCases.ShoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

public class ShoppingCartTests extends BaseTest {

@Test
    public void testAddToCartWithEmptyItems(){
    landingPage.getMyShoppingCartInTopbar().click();
    landingPage.waitTillElementIsVisibleUsingWebElement(landingPage.getShoppingCartSideFragmentContainer());
    Assert.assertEquals(landingPage.getEmptyCartLabelInShoppingCartSideFragment().getText(),"Your shopping cart is empty!");


}

@Test
    public void testEditCartButtonOnMyShoppingCartFragment(){
    Search search = landingPage.searchProductWithName("imac");
    WebElement item =  search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(item).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(item));
    search.getAddToCartActionButton(item).click();
    search.getMyShoppingCartInTopbar().click();
    search.waitTillElementIsVisibleUsingWebElement(landingPage.getEditCartButtonInShoppingCartSideFragment());
    search.getEditCartButtonInShoppingCartSideFragment().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    Assert.assertEquals(mDriver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
}


}
