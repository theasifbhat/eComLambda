package testCases.Search;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;

import java.util.LinkedHashSet;
import java.util.Set;

public class SearchTests extends BaseTest {

    @Test
    public void testSearchFunctionality(){
        String productName= "ipho";
        landingPage.searchProductWithName(productName);
        Search search = new Search(mDriver);
        search.getSearchResultNames().forEach(item->{
           if (!item.toLowerCase().contains(productName)){
               Assert.fail("Search functionality not working properly");
           }
        });

    }


    @Test
    public void testSearchWithNonExistingProduct(){
        String productName = "fitbit";
        landingPage.searchProductWithName(productName);
        Search search = new Search(mDriver);
        if(!search.searchProductsWithNonExistingProductName()){
            Assert.fail("Product Found with a non existing product name search.");
        }
    }

    @Test
    public void testSearchWithEmptyTerm(){
        landingPage.clickOnSearchButtonWithoutText();
        Search search = new Search(mDriver);
        if (search.getSearchResultNames().isEmpty()){
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
        testSearchFunctionality();
    }

    @Test
    public void testSearchWithGenericTerm(){
        String productName = "mac";
        landingPage.searchProductWithName(productName);
        Search search = new Search(mDriver);
        Set<String> items = new LinkedHashSet<>(search.getSearchResultNames());
        if (items.size()<2){
            Assert.fail("Items with same name found");
        }
    }

    @Test
    public void testSearchWithSearchCriteriaField(){
        String productName="mac";
        landingPage.clickOnSearchButtonWithoutText();
        Search search = new Search(mDriver);
        waitForPageLoaded();
        search.setSearchBoxText(productName);
        search.clickOnSearchButton();
        search.getSearchResultNames().forEach(item->{
            if (!item.toLowerCase().contains(productName)){
                Assert.fail("Search functionality in search criteria not working properly");
            }
        });

    }


}
