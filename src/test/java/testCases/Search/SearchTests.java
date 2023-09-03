package testCases.Search;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Product.Product;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

import java.util.*;

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

    @Test
    public void testSearchLabelTitleUrl(){
        String product = "imac";
        Search search = landingPage.searchProductWithName(product);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(search.getSearchQueryLabel().getText().toLowerCase().contains(product));
        softAssert.assertEquals(mDriver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search="+product);
        softAssert.assertEquals(mDriver.getTitle().toLowerCase(),"search - imac");
        softAssert.assertAll();
    }


    @Test
    public void testListViewIsWorking(){
        String product = "imac";
        Search search = landingPage.searchProductWithName(product);
        search.clickOnListView();
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItemsContainers());
        Assert.assertEquals(search.getSearchItemsContainerClass(),"product-layout product-list col-12");
    }

    @Test
    public void testGridViewIsWorking(){
        String product = "imac";
        Search search = landingPage.searchProductWithName(product);
        search.clickOnListView();
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItemsContainers());
        search.clickOnGridView();
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItemsContainers());
        Assert.assertEquals(search.getSearchItemsContainerClass(),"product-layout product-grid no-desc col-xl-4 col-lg-4 col-md-4 col-sm-6 col-6");
    }

    @Test
    public void testSortByIncreasingName(){
        Search search = landingPage.searchProductWithName("mac");
        List<String> beforeSort = new java.util.ArrayList<>(search.getSearchResultNames().stream().map(String::toLowerCase).toList());  // in order to make sort work each item in list<String> needs to be converted to lowercase
        search.selectSortFromSortDropdown(4);
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItems());
        List <String> afterSortFromFrontEnd = search.getSearchResultNames().stream().map(String::toLowerCase).toList();

        Collections.sort(beforeSort);

        if (!beforeSort.equals(afterSortFromFrontEnd)){
            Assert.fail("sort order does not match");
        }

    }

    @Test
    public void testSortByDecreasingOrder(){
        Search search = landingPage.searchProductWithName("mac");
        List<String> beforeSort = new java.util.ArrayList<>(search.getSearchResultNames().stream().map(String::toLowerCase).toList());
        search.selectSortFromSortDropdown(5);
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItems());
        List <String> afterSortFromFrontEnd = search.getSearchResultNames().stream().map(String::toLowerCase).toList();

        beforeSort.sort(Collections.reverseOrder());

        if (!beforeSort.equals(afterSortFromFrontEnd)){
            Assert.fail("sort order does not match");
        }
    }

    @Test
    public void testSortByIncreasingPrice(){
        Search search = landingPage.searchProductWithName("mac");
        List<String> beforeSort = new java.util.ArrayList<>(search.getSearchResultItemPrice().stream().map(WebElement::getText).toList());
        search.selectSortFromSortDropdown(6);
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItemPrice());
        List<String> afterSortFromFrontEnd = search.getSearchResultItemPrice().stream().map(WebElement::getText).toList();

        List<Float> convertedPriceFromString = GlobalFunctions.convertListOfStringOfPricesToListOfFloat(beforeSort);
        Collections.sort(convertedPriceFromString);

        List<Float> convertedPriceFromFrontEnd = GlobalFunctions.convertListOfStringOfPricesToListOfFloat(afterSortFromFrontEnd);

        if (!convertedPriceFromFrontEnd.equals(convertedPriceFromString)){
            Assert.fail("sorting price from increasing order not working");
        }
    }


    @Test
    public void testSortByDecreasingPrice(){
        Search search = landingPage.searchProductWithName("mac");
        List<String> beforeSort = new java.util.ArrayList<>(search.getSearchResultItemPrice().stream().map(WebElement::getText).toList());
        search.selectSortFromSortDropdown(7);
        search.waitTillElementsAreVisibleUsingWebElement(search.getSearchResultItemPrice());
        List<String> afterSortFromFrontEnd = search.getSearchResultItemPrice().stream().map(WebElement::getText).toList();

        List<Float> convertedPriceFromString = GlobalFunctions.convertListOfStringOfPricesToListOfFloat(beforeSort);
        convertedPriceFromString.sort(Collections.reverseOrder());

        List<Float> convertedPriceFromFrontEnd = GlobalFunctions.convertListOfStringOfPricesToListOfFloat(afterSortFromFrontEnd);

        if (!convertedPriceFromFrontEnd.equals(convertedPriceFromString)){
            Assert.fail("sorting price from decreasing order not working");
        }
    }



}
