package testCases.Product;

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

    System.out.println("pr"+product.getSmallImages().toString());
    //need to look into this, not returning anything

    product.getSmallImages().forEach(item->{
        System.out.println("item "+item.getAttribute("href"));
        src.add(item.getAttribute("href"));
    });


    System.out.println("length "+product.getSmallImages().size());

    for (String items : src){
        System.out.println("item "+items);
    }

    //determine if the webelement is iframe of figure
    //get the src of figure or iframe
    //compare with the src from list


}

}
