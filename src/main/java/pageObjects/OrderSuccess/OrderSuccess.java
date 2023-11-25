package pageObjects.OrderSuccess;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage.BasePage;

public class OrderSuccess extends BasePage {

    WebDriver mDriver;
    public OrderSuccess(WebDriver mDriver) {
        super(mDriver);
    }

    @FindBy(xpath = "//div[@id='content']/h1")
    @Getter
    private WebElement orderSuccessLabel;


}
