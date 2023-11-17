package testCases.Landing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Search.Search;
import pageObjects.ShoppingCart.ShoppingCart;
import testCases.BaseTest.BaseTest;
import utilities.GlobalFunctions;

import java.util.ArrayList;
import java.util.List;

public class LandingTests extends BaseTest {



@Test
public void testHeroImages(){
    SoftAssert sf = new SoftAssert();
    int count =0;
    for (WebElement item: landingPage.getHeroItems()) {
        sf.assertEquals(landingPage.getImageElementFromHeroItem(item).getAttribute("src"),landingPage.getHeroItems().get(count));
        count++;
    }

    sf.assertAll();
}


@Test
    public void testNextButton() throws InterruptedException{
    Actions actions = new Actions(mDriver);
    for (WebElement item : landingPage.getHeroItems()){
        Thread.sleep(1000);   // hard wait for 1 second as the class changes temporarily while transitioning
        if(!item.getAttribute("class").contains("active")){
            System.out.println("this "+item.getAttribute("class"));
            Assert.fail("Next button is not working properly");
        }
        actions.moveToElement(landingPage.getNextButtonHero()).click().build().perform();
    }
}

@Test
    public void testPrevButton() throws InterruptedException{
    List<WebElement> heroItems= landingPage.getHeroItems();
    Actions actions = new Actions(mDriver);
    for (int i = 0; i < heroItems.size(); i++) {
        Thread.sleep(500);
        actions.moveToElement(landingPage.getNextButtonHero()).click().build().perform();
    }

    int count = heroItems.size()-1;
    for (WebElement item : heroItems){
        Thread.sleep(800);   // hard wait for 1 second as the class changes temporarily while transitioning
        if(!heroItems.get(count).getAttribute("class").contains("active")){
            Assert.fail("Prev button is not working properly");
        }
        actions.moveToElement(landingPage.getPreviousButtonHero()).click().build().perform();
        count--;
    }
}


@Test
    public void testAutomaticTransitionOnHeroSection() throws InterruptedException{

    for (WebElement item : landingPage.getHeroItems()){
        if(!item.getAttribute("class").contains("active")){
            Assert.fail("Automatic transition is not working properly");
        }
        Thread.sleep(5800);
    }
}


@Test
    public void testCheckoutWithNoProductInCart(){
    landingPage.getMyShoppingCartInTopbar().click();
    landingPage.waitTillElementIsVisibleUsingWebElement(landingPage.getCheckoutButtonInShoppingCartSideFragment());
    landingPage.getCheckoutButtonInShoppingCartSideFragment().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    Assert.assertEquals(mDriver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
}

@Test
    public void testCheckoutWithProductInCart(){
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem=search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getCheckOutButtonInToast());
    search.getCheckOutButtonInToast().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    Assert.assertEquals(mDriver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout");
}


@Test
    public void testCheckoutWithCartHeaderIcon(){
    Search search = landingPage.searchProductWithName("iMac");
    WebElement searchItem=search.getSearchResultWebElements().get(0);
    Actions actions = new Actions(mDriver);
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    actions.moveToElement(searchItem).build().perform();
    search.waitTillElementIsVisibleUsingWebElement(search.getAddToCartActionButton(searchItem));
    search.getAddToCartActionButton(searchItem).click();
    search.waitTillElementIsVisibleUsingWebElement(search.getToastMessageContainer());
    search.closeToastBox();
    landingPage.getMyShoppingCartInTopbar().click();
    landingPage.waitTillElementIsVisibleUsingWebElement(landingPage.getCheckoutButtonInShoppingCartSideFragment());
    landingPage.getCheckoutButtonInShoppingCartSideFragment().click();
    GlobalFunctions.waitForPageLoad(mDriver);
    Assert.assertEquals(mDriver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout");
}


}
