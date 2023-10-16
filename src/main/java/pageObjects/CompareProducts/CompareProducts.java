package pageObjects.CompareProducts;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

import java.util.List;

public class CompareProducts extends BasePage {

    WebDriver mDriver;
    public CompareProducts(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }



    @Getter
    @FindBy(xpath = "//table[@class='table table-responsive table-bordered']//input[@value='Add to Cart']")
    private List<WebElement> addToCartButtons;

}
