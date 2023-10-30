package testCases.ShoppingCart;

import org.openqa.selenium.JavascriptExecutor;
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

import java.util.Arrays;

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
    String testProductName = "iPhone";
    Search search = landingPage.searchProductWithName(testProductName);
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);

    String image = product.getMainImage().getAttribute("src");
    String productName = product.getProductTitle().getText();
    String productModel = product.getProductModel().getText();
    String quantity = product.getCurrentQuantity();
    String unitPrice = product.getUnitPrice().getText();

    product.getAddToCartButton().click();
    product.waitTillElementIsVisibleUsingWebElement(product.getToastMessageContainer());
    SoftAssert sf = new SoftAssert();
    sf.assertEquals(product.getSanitizedToastText(),"Success: You have added "+testProductName+" to your shopping cart !");
    product.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);

    shoppingCart.getShoppingCartItems().forEach(item->{
    String sanitizedCartImage = GlobalFunctions.sanitizeImageUrl(item.findElement(shoppingCart.getCartItemPhotoElement()).getAttribute("src"));
    String sanitizedProductImage = GlobalFunctions.sanitizeImageUrl(image);
    JavascriptExecutor js = (JavascriptExecutor) mDriver;
    String qua = (String) js.executeScript("return arguments[0].value",item.findElement(shoppingCart.getQuantityField()));

    sf.assertEquals(sanitizedProductImage,sanitizedCartImage);
    sf.assertEquals(item.findElement(shoppingCart.getCartItemName()).getText(),productName);
    sf.assertEquals(item.findElement(shoppingCart.getModelName()).getText(),productModel);
    sf.assertEquals(qua,quantity);
    sf.assertEquals(item.findElement(shoppingCart.getCartItemUnitPrice()).getText(),unitPrice);
    sf.assertEquals(item.findElement(shoppingCart.getCartItemTotalPrice()).getText(),unitPrice);

    });
    sf.assertEquals(shoppingCart.getFinalTotal().getText(),unitPrice);  //for time being
    sf.assertAll();

}


@Test
    public void testRemoveButtonOnShoppingCartItem(){
    String testProductName = "iPhone";
    Search search = landingPage.searchProductWithName(testProductName);
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);

    product.getAddToCartButton().click();
    product.waitTillElementIsVisibleUsingWebElement(product.getToastMessageContainer());
    SoftAssert sf = new SoftAssert();
    sf.assertEquals(product.getSanitizedToastText(),"Success: You have added "+testProductName+" to your shopping cart !");
    product.getShoppingCartLinkInToast().click();
    ShoppingCart shoppingCart = new ShoppingCart(mDriver);

    shoppingCart.getShoppingCartItems().forEach(item->{
        item.findElement(shoppingCart.getRemoveItemButton()).click();
    });
    GlobalFunctions.waitForPageLoad(mDriver);
    Assert.assertEquals(shoppingCart.getEmptyShoppingCartLabel().getText(),"Your shopping cart is empty!");

}


}
