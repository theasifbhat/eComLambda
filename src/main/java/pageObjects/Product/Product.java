package pageObjects.Product;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

public class Product extends BasePage {
WebDriver mDriver;


public Product(WebDriver mDriver){
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
}

@Getter
@FindBy(xpath = "//div[@id='mz-design-tab-216814-0']/div/div/div")
private WebElement productDescription;



}
