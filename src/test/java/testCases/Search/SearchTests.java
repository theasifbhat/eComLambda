package testCases.Search;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;

import java.util.LinkedHashSet;
import java.util.Set;

public class SearchTests extends BaseTest {

    @Test
    public void testSearchFunctionality(){
        String productName= "ipho";
        Search search =  landingPage.searchProductWithName(productName);
        search.getSearchResultNames().forEach(item->{
           if (!item.toLowerCase().contains(productName)){
               Assert.fail("Search functionality not working properly");
           }
        });

    }


    @Test
    public void testSearchWithNonExistingProduct(){
        String productName = "fitbit";
        Search search =landingPage.searchProductWithName(productName);
        if(!search.searchProductsWithNonExistingProductName()){
            Assert.fail("Product Found with a non existing product name search.");
        }
    }

    @Test
    public void testSearchWithEmptyTerm(){
        Search search = landingPage.clickOnSearchButtonWithoutText();
        if (search.getSearchResultNames().isEmpty()){  //rep !
            Assert.fail("search without text not working");
        }

    }

    @Test
    public void testSuggestedItemsWhenSearching(){
        landingPage.getSearchSuggestionWithName("mac");
    }


    @Test
    public void testSearchFunctionalityWithLogin(){
        landingPage.login();
        landingPage.goToHomepage();
        testSearchFunctionality();
    }

    @Test
    public void testSearchWithGenericTerm(){
        String productName = "mac";
        Search search = landingPage.searchProductWithName(productName);
        Set<String> items = new LinkedHashSet<>(search.getSearchResultNames());
        System.out.println("size of items "+items.size());
        if (items.size()<2){
            Assert.fail("Items with same name found");
        }
    }

    @Test
    public void testSearchWithSearchCriteriaField(){
        String productName="mac";
        Search search = landingPage.clickOnSearchButtonWithoutText();
        search.setSearchBoxText(productName);
        search.clickOnSearchButton();
        search.getSearchResultNames().forEach(item->{
            if (!item.toLowerCase().contains(productName)){   // add !
                Assert.fail("Search functionality in search criteria not working properly");
            }
        });
    }

    @Test
    public void testSearchWithDescription(){
        String productDesc = "ilife";
        Search search = landingPage.clickOnSearchButtonWithoutText();
        search.setSearchInDescriptionCheckboxToTrue();
        search.setSearchBoxText(productDesc);
        search.clickOnSearchButton();
        search.getSearchResultWebElements().forEach(item->{
            search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultWebElements());
            item.click();
            Product product = new Product(mDriver);
            Assert.assertTrue(product.getProductDescription().getText().toLowerCase().contains(productDesc));
            mDriver.navigate().back();
        });
    }


}
