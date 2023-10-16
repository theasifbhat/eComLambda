package testCases.CompareProducts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.CompareProducts.CompareProducts;
import pageObjects.Search.Search;
import testCases.BaseTest.BaseTest;

public class CompareProductsTests extends BaseTest {


 @Test
 public void testAddToCartFromComparePage(){
     Search search = landingPage.searchProductWithName("imac");
     Actions actions = new Actions(mDriver);
     WebElement selectedSearchItem = search.getSearchResultWebElements().get(0);
     actions.moveToElement(selectedSearchItem).build().perform();
     search.waitTillElementIsVisibleUsingWebElement(search.getAddToCompareActionButton(selectedSearchItem));
     search.getAddToCompareActionButton(selectedSearchItem).click();
     SoftAssert sf = new SoftAssert();
     sf.assertEquals(search.getSanitizedToastText(),"Success: You have added iMac to your product comparison !");

     search.closeToastBox();

     selectedSearchItem = search.getSearchResultWebElements().get(1);
     actions.moveToElement(selectedSearchItem).build().perform();
     search.waitTillElementIsVisibleUsingWebElement(search.getAddToCompareActionButton(selectedSearchItem));
     search.getAddToCompareActionButton(selectedSearchItem).click();
     sf.assertEquals(search.getSanitizedToastText(),"Success: You have added iMac to your product comparison !");

     CompareProducts compareProducts = search.clickOnGoToCompareScreenButtonOnToast();

     compareProducts.getAddToCartButtons().forEach(item->{
         item.click();
         sf.assertEquals(compareProducts.getSanitizedToastText(),"Success: You have added iMac to your shopping cart !");
         compareProducts.closeToastBox();
     });

     sf.assertAll();
 }



}
