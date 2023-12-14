package pageObjects.ConfirmOrder;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import pageObjects.OrderSuccess.OrderSuccess;

public class ConfirmOrder extends BasePage {
    WebDriver mDriver;
    public ConfirmOrder(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }
@FindBy(id = "button-confirm")
@Getter
private WebElement confirmOrderButton;



public OrderSuccess clickConfirmOrderButton() {
   new Actions(mDriver).moveToElement(confirmOrderButton).click().build().perform();
   return new OrderSuccess(mDriver);
}
}
