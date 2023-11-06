package pageObjects.Landing;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.BasePage.BasePage;
import pageObjects.Login.Login;
import pageObjects.Product.Product;
import pageObjects.Register.Register;
import pageObjects.Search.Search;
import utilities.GlobalFunctions;

import java.util.List;

public class Landing extends BasePage {
    WebDriver mDriver;

    public Landing(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(xpath = "//div[@class='product-thumb']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//a[contains(@href, 'register')]")
    private WebElement registerLink;


    //hero section

    @FindBy(css = ".carousel-inner")
    @Getter
    private WebElement heroSectionContainer;

    @FindBy(xpath = "//div[@id='mz-carousel-213240']//div[@class='carousel-inner']/div")
    @Getter
    private List<WebElement> heroItems;   // there are 3 items and this can be indexed accordingly

    //hero locators

    By linkOfHeroItem = By.xpath("a[@class='d-block w-100']");
    By imageSrc = By.xpath("a/img");

    //hero actions

    public WebElement getImageElementFromHeroItem(WebElement heroItem){
        return heroItem.findElement(imageSrc);
    }

    public String getOnClickedLinkOfHeroElement(WebElement heroItem){
        return heroItem.findElement(linkOfHeroItem).getAttribute("href");
    }


    public void goToLandingPage() {
        mDriver.get("https://"+
                GlobalFunctions.getPropertyFromPropertyFile("url"));   //hardcoded https because updateproperties messes up with the url
    }
    public Register goToRegisterPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(getMyAccountNav()).build().perform();
        waitTillElementIsVisibleUsingWebElement(registerLink);
        actions.click(registerLink).build().perform();
        return new Register(mDriver);
    }

    public Login goToLoginPage(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(getMyAccountNav()).build().perform();
        waitTillElementIsVisibleUsingWebElement(getLoginLink());
        actions.click(getLoginLink()).build().perform();
        return new Login(mDriver);
    }




}
