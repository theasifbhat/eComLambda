package testCases.Wishlist;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasePage.BasePage;
import pageObjects.MyAccount.MyAccount;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import pageObjects.Wishlist.Wishlist;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

import java.util.List;

public class WishlistTests extends BaseTest {

    @Test
    public void checkAddToCartFromWishlist() {
        landingPage.login().goToHomepage();
        Search search = landingPage.searchProductWithName("iMac");
        Actions actions = new Actions(mDriver);
        WebElement selectedSearchItem = search.getSearchResultWebElements().get(0);
        actions.moveToElement(selectedSearchItem).build().perform();
        search.waitTillElementIsVisibleUsingWebElement(search.getAddToWishlistActionButton(selectedSearchItem));
        search.getAddToWishlistActionButton(selectedSearchItem).click();
        Assert.assertEquals(search.getSanitizedToastText(), "Success: You have added iMac to your wish list !", "Item not added to wishlist");
        search.closeToastBox();
        Wishlist wishlist = search.clickOnTopBarWishlistIcon();
        Assert.assertFalse(wishlist.getWishlistItems().isEmpty(), "No item in wishlist");

        wishlist.getWishlistItems().forEach(item -> {
            wishlist.getWishlistItemAddToCartButton(item).click();
            wishlist.waitTillElementIsVisibleUsingWebElement(wishlist.getToastMessageContainer());
            Assert.assertEquals(wishlist.getSanitizedToastText(), "Success: You have added iMac to your shopping cart !");
            wishlist.closeToastBox();
            wishlist.getWishlistItemRemoveButton(item).click();
            Assert.assertEquals(wishlist.getWishlistItems().size(), 0, "Wishlist Item not deleted");
        });


    }

}
