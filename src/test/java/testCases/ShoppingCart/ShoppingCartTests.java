package testCases.ShoppingCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import testCases.BaseTest.BaseTest;

public class ShoppingCartTests extends BaseTest {

@Test
public void testAddToCartWithEmptyItems(){

    landingPage.getMyShoppingCartInTopbar().click();
    landingPage.waitTillElementIsVisibleUsingWebElement(landingPage.getShoppingCartSideFragmentContainer());
    Assert.assertEquals(landingPage.getEmptyCartLabelInShoppingCartSideFragment().getText(),"Your shopping cart is empty!");


}


}
