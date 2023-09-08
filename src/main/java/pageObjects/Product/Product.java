package pageObjects.Product;

import lombok.Getter;
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

    public void clickOnAddToCart() {
        addToCartButton.click();
    }

    public void clickOnThumbnail(){
        mainImage.click();
    }





}
