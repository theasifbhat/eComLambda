package pageObjects.ShoppingCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

public class ShoppingCart extends BasePage {
    WebDriver mDriver;
    public ShoppingCart(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }






}
