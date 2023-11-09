package testCases.Landing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testCases.BaseTest.BaseTest;

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
    Actions actions = new Actions(mDriver);
    for (int i = 0; i < landingPage.getHeroItems().size(); i++) {
        Thread.sleep(500);
        actions.moveToElement(landingPage.getNextButtonHero()).click().build().perform();
    }

    // need to iterate through the list in reverse order
    for (WebElement item : landingPage.getHeroItems()){
        Thread.sleep(500);   // hard wait for 1 second as the class changes temporarily while transitioning
        if(!item.getAttribute("class").contains("active")){
            System.out.println("this "+item.getAttribute("class"));
            Assert.fail("Prev button is not working properly");
        }
        actions.moveToElement(landingPage.getPreviousButtonHero()).click().build().perform();
    }
}





}
