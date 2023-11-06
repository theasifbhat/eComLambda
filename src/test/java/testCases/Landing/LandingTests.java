package testCases.Landing;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testCases.BaseTest.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class LandingTests extends BaseTest {


@Test
public void testHeroImages(){
    List<String> imageUrls = new ArrayList<>();
    imageUrls.add("https://ecommerce-playground.lambdatest.io/image/cache/catalog/maza/demo/mz_poco/megastore-2/banner/main-banner-1600x500.webp");
    imageUrls.add("https://ecommerce-playground.lambdatest.io/image/cache/catalog/maza/demo/mz_poco/megastore-2/banner/main-banner2-1600x500.webp");
    imageUrls.add("https://ecommerce-playground.lambdatest.io/image/cache/catalog/maza/demo/mz_poco/megastore-2/banner/main-banner3-1600x500.webp");

    SoftAssert sf = new SoftAssert();
    int count =0;
    for (WebElement item: landingPage.getHeroItems()) {
        sf.assertEquals(landingPage.getImageElementFromHeroItem(item).getAttribute("src"),imageUrls.get(count));
        count++;
    }

    sf.assertAll();
}

}
