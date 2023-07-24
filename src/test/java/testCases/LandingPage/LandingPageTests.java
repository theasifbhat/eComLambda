package testCases.LandingPage;

import org.testng.annotations.Test;
import testCases.BaseTest.BaseTest;

public class LandingPageTests extends BaseTest {

    @Test
    public void testSearchFunctionality(){
    landingPage.searchProductWithName("iPhone");
    }


    @Test
    public void testSuggestedItemsWhenSearching(){
        landingPage.getSearchSuggestionWithName("mac");
    }
}
