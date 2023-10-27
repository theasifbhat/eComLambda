package testCases.ShoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import pageObjects.ShoppingCart.ShoppingCart;
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


@Test
    public void testUpdateShoppingCartQuantity(){
    Search search = landingPage.searchProductWithName("imac");
    WebElement item =  search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    actions.moveToElement(item).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(item));
    search.getAddToCartActionButton(item).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getShoppingCartLinkInToast());
    search.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);
    shoppingCart.updateQuantityOfShippingItems(3);
    Assert.assertEquals(shoppingCart.getTopbarMessage(),"Success: You have modified your shopping cart!\n" +
            "×");
}


@Test
    public void testImageNameModelQuantityUnitPriceTotal(){
    Search search = landingPage.searchProductWithName("iMac");
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);
    String unitPrice = product.getUnitPrice().getText();
    String quantity = product.getCurrentQuantity();

//other params
    product.getAddToCartButton().click();
    product.waitTillElementIsVisibleUsingWebElement(product.getToastMessageContainer());
    SoftAssert sf = new SoftAssert();
    sf.assertEquals(product.getSanitizedToastText(),"Success: You have added iMac to your shopping cart !");
    product.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);

    shoppingCart.getShoppingCartItems().forEach(item->{
       // sf.assertEquals(item.findElement(shoppingCart.quantityField).getAttribute("innerHTML"),quantity);
        System.out.println("quantity from shopping cart:"+item.findElement(shoppingCart.quantityField).getAttribute("innerHTML"));
        System.out.println("quantity from product page:"+quantity);

        System.out.println("unitprice from shopping cart:"+item.findElement(shoppingCart.cartItemUnitPrice).getText());
        System.out.println("unitprice from product page:"+unitPrice);

        sf.assertEquals(item.findElement(shoppingCart.cartItemUnitPrice).getText(),unitPrice);
    });

    sf.assertAll();

}


}
