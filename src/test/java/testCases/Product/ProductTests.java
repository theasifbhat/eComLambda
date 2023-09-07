package testCases.Product;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ProductTests extends BaseTest {

@Test
public void testMainThumbnailClick(){

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


    //determine if the webelement is iframe of figure
    //get the src of figure or iframe
    //compare with the src from list

}
}
