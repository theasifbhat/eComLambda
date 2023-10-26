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
    private List<WebElement> shoppingCartItems;


    //locators for line items in shopping cart items

    By quantityField = By.xpath("td//input");
    By updateQuantityButton = By.xpath("td//button[@type='submit']");


    public void updateQuantityOfShippingItems(int quantity){
        shoppingCartItems.forEach(item->{

        item.findElement(quantityField).clear();
        item.findElement(quantityField).sendKeys(""+quantity);
        item.findElement(updateQuantityButton).click();
        GlobalFunctions.waitForPageLoad(mDriver);
        });
    }



}
