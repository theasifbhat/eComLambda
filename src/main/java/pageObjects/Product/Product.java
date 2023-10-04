package pageObjects.Product;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

import java.util.List;

public class Product extends BasePage {
    WebDriver mDriver;


    public Product(WebDriver mDriver) {
        super(mDriver);
        this.mDriver = mDriver;
        PageFactory.initElements(mDriver, this);
    }

    @Getter
    @FindBy(xpath = "//div[@id='mz-design-tab-216814-0']/div/div/div")
    private WebElement productDescription;

    @Getter
    @FindBy(css = "#entry_216842>button")
    private WebElement addToCartButton;

    @Getter
    @FindBy(xpath = "(//div[@id='image-gallery-216811']//img)[1]")
    private WebElement mainImage;

    @Getter
    @FindBy(xpath = "//div[@style='width: 20%']//div[contains(@id,'swiper-wrapper')]//a")
    private List<WebElement> smallImages;

    @Getter
    @FindBy(css = "button[title='Previous (Left arrow key)']")
    private WebElement previousImageButton;

    @Getter
    @FindBy(css = "button[title='Next (Right arrow key)']")
    private WebElement nextImageButton;

    @Getter
    @FindBy(xpath = "//button[@title='Close (Esc)']/following-sibling::*")
    private WebElement wrappingElementOfProductSnapshot;

    @Getter
    @FindBy(xpath = "//button[@title='Close (Esc)']/following-sibling::*//img")
    private WebElement imageInFigure;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216826']//span[contains(@class,'badge')]")
    private WebElement productStatusText;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216841']//input[@name='quantity']")
    private WebElement purchasingQuantity;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216841']//input[@name='quantity']/following-sibling::div/button")
    private WebElement plusButtonForQuantity;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216841']//input[@name='quantity']/preceding-sibling::div/button")
    private WebElement minusButtonForQuantity;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216852']/div")
    private WebElement minimumQuantityDiv;

    @Getter
    @FindBy(xpath = "//div[@id='entry_216861']//div[@class='row']/div")
    private List<WebElement> relatedProducts;


    // locators for action bar elements in related product items

    //locators for actions on result items

    By addToCartAction = By.xpath("div//button[@title='Add to Cart']");
    By addToWishlistAction = By.xpath("div[@class='product-action']/button[@title='Add to Wish List']");

    //takes the argument of a search result from search page
    public WebElement getAddToCartActionButton(WebElement searchResultItem){
        waitTillElementIsVisibleUsingWebElement(searchResultItem.findElement(addToCartAction));
        return searchResultItem.findElement(addToCartAction);
    }

    public WebElement getAddToWishlistActionButton(WebElement searchResultItem){
        waitTillElementIsVisibleUsingWebElement(searchResultItem.findElement(addToWishlistAction));
        return searchResultItem.findElement(addToWishlistAction);
    }


    //main methods

    public void clickOnThumbnail(){
        mainImage.click();
    }

    public String getCurrentQuantity(){String locator = "//div[@id='entry_216841']//input[@name='quantity']";
        JavascriptExecutor js = (JavascriptExecutor) mDriver;
        return (String) js.executeScript(" return arguments[0].value;",purchasingQuantity);
    }


    public void clickOnAddToWishlist() {
    }
}
