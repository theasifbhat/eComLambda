package testCases.Product;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ProductTests extends BaseTest {

@Test
public void testClickingOnNextButtonWhenThumbnailIsPreviewed(){

    Search search = landingPage.searchProductWithName("mac");
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);
    List<String> src = new ArrayList<>();

    product.getSmallImages().forEach(item->{
        src.add(item.getAttribute("href"));
    });

    product.clickOnThumbnail();
    product.waitTillElementIsVisibleUsingWebElement(product.getWrappingElementOfProductSnapshot());

    List<String> frontEnd = new ArrayList<>();

    for(int i=0; i<src.size(); i++){
        product.getNextImageButton().click();
    if (product.getWrappingElementOfProductSnapshot().getTagName().equals("iframe")){
        frontEnd.add(product.getWrappingElementOfProductSnapshot().getAttribute("src"));
    }
    else{
        frontEnd.add(product.getImageInFigure().getAttribute("src"));
    }
    }
    Assert.assertEquals(frontEnd,src);
}


    @Test
    public void testEscIsWorkingOnThumbnailPreview(){
        Search search = landingPage.searchProductWithName("mac");
        search.getSearchResultWebElements().get(0).click();
        Product product = new Product(mDriver);
        product.clickOnThumbnail();
        product.waitTillElementIsVisibleUsingWebElement(product.getWrappingElementOfProductSnapshot());
        new Actions(mDriver).sendKeys(Keys.ESCAPE).build().perform();
        product.waitTillElementIsInvisible(product.getWrappingElementOfProductSnapshot());

        Assert.assertFalse(product.isElementDisplayed(product.getWrappingElementOfProductSnapshot()));
}


@Test
    public void testAvailabilityStatusIsDisplayed(){
    Search search = landingPage.searchProductWithName("mac");
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);
    if(!(product.getProductStatusText().getText().equalsIgnoreCase("In Stock")
            || product.getProductStatusText().getText().equalsIgnoreCase("Out of Stock")
            || product.getProductStatusText().getText().equalsIgnoreCase("Limited Stock"))) {
        Assert.fail("Availability status not working");
    }
}

@Test
    public void testDefaultQuantityOfProduct(){
    Search search = landingPage.searchProductWithName("mac");
    search.getSearchResultWebElements().get(0).click();
    Product product = new Product(mDriver);
    Assert.assertEquals(product.getCurrentQuantity(), "1");
}

@Test
    public void testDefaultQuantityAsTwo(){
    Product product = landingPage.clickOnFirstSuggestionWithText("Apple Cinema 30");

    if (product.isElementDisplayed(product.getMinimumQuantityDiv())){
        Assert.assertEquals(product.getCurrentQuantity(),"2");
        product.getMinusButtonForQuantity().click();
        Assert.assertEquals(product.getCurrentQuantity(),"2");
    }

    else {
        Assert.fail("the entered product in this test case does not have 2 as minimum quantity");
    }
}

    @Test
    public void testAddToCartProduct(){
        Search search = landingPage.searchProductWithName("iMac");
        search.getSearchResultWebElements().get(0).click();
        Product product = new Product(mDriver);
        product.getAddToCartButton().click();
        product.waitTillElementIsVisibleUsingWebElement(product.getToastMessageContainer());
        Assert.assertEquals(product.getSanitizedToastText(),"Success: You have added iMac to your shopping cart !");
    }


    @Test
    public void testAddToCartFromRelatedProducts(){
        Search search = landingPage.searchProductWithName("iMac");
        search.getSearchResultWebElements().get(0).click();
        Product product = new Product(mDriver);
        product.waitTillElementsAreVisibleUsingWebElement(product.getRelatedProducts());
        Actions actions = new Actions(mDriver);
        actions.moveToElement(product.getRelatedProducts().get(0)).build().perform();
        product.getAddToCartActionButton(product.getRelatedProducts().get(0)).click();
        product.clickOnAddToCartInRelatedItemPopup();
        product.waitTillElementIsInvisible(product.getToastMessageContainer());
        Assert.assertEquals(product.getSanitizedToastText(),"Success: You have added Apple Cinema 30\" to your shopping cart !");

    }




}
