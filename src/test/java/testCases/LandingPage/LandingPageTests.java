package testCases.LandingPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import testCases.BaseTest.BaseTest;

public class LandingPageTests extends BaseTest {

    @Test
    public void testSearchFunctionality(){
        String productName= "iPhone";
        landingPage.searchProductWithName(productName);
        landingPage.getSearchResultNames().forEach(item->{
           if (!item.contains(productName)){
               Assert.fail("Search functionality not working properly");
           }
        });

    }


    @Test
    public void testSearchWithNonExistingProduct(){
        String productName = "fitbit";
        landingPage.searchProductWithName(productName);
        if(!landingPage.searchProductsWithNonExistingProductName()){
            Assert.fail("Product Found with a non existing product name search.");
        }
    }

    @Test
    public void testSearchWithEmptyTerm(){
        if (!landingPage.searchWithEmptyProductName()){
            Assert.fail("No search occurred");
        }

    }






    @Test
    public void testSuggestedItemsWhenSearching(){
        landingPage.getSearchSuggestionWithName("mac");
    }
}
