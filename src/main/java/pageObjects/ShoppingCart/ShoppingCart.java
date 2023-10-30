package pageObjects.ShoppingCart;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import utilities.GlobalFunctions;

import java.util.List;

public class ShoppingCart extends BasePage {
    WebDriver mDriver;
    public ShoppingCart(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }



    @FindBy(xpath = "//div[@class='table-responsive']/table/tbody/tr")
    @Getter
    private List<WebElement> shoppingCartItems;

    @FindBy(xpath = "//div[@id='content']//p")
    @Getter
    private WebElement emptyShoppingCartLabel;



    //total table

    @FindBy(xpath = "(//div[@class='col-md-4']/table/tbody//tr)[4]//strong")
    @Getter
    private WebElement finalTotal;



    //locators for line items in shopping cart items


    @Getter By updateQuantityButton = By.xpath("td//button[@type='submit']");
    @Getter By removeItemButton = By.xpath("td//button[@type='button']");

    @Getter By quantityField = By.xpath("td//input");
    @Getter By cartItemPhotoElement = By.xpath("td[@class='text-center']//img");

    @Getter By cartItemName = By.xpath("td[@class='text-left']//a");

    @Getter By modelName = By.xpath("td[@class='text-left']//a/parent::td/following-sibling::td[1]");

    @Getter By cartItemUnitPrice = By.xpath("td[@class='text-left']//a/parent::td/following-sibling::td[3]");

    @Getter By cartItemTotalPrice = By.xpath("td[@class='text-left']//a/parent::td/following-sibling::td[4]");



    public void updateQuantityOfShippingItems(int quantity){
        shoppingCartItems.forEach(item->{
        item.findElement(quantityField).clear();
        item.findElement(quantityField).sendKeys(""+quantity);
        item.findElement(updateQuantityButton).click();
        GlobalFunctions.waitForPageLoad(mDriver);
        });
    }

    public void compareQuantityUnitPrice(String quantity, String unitPrice){
        for (WebElement shoppingCartItem : shoppingCartItems) {
            shoppingCartItem.findElement(quantityField).getText().equals(quantity);
            shoppingCartItem.findElement(cartItemUnitPrice).getText().equals(unitPrice);
        }
    }



}
