package pageObjects.Wishlist;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

import java.util.List;

public class Wishlist extends BasePage {

    WebDriver mDriver;

    public Wishlist(WebDriver mDriver) {
        super(mDriver);
        this.mDriver = mDriver;
        PageFactory.initElements(mDriver,this);
    }


    @Getter
    @FindBy(xpath = "//div[@id='content']//tbody//tr")
    private List<WebElement> wishlistItems;

    By wishlistItemText = By.xpath("(td)[2]");
    By wishListItemAddToCartButton = By.xpath("td/button");
    By wishlistItemRemoveButton = By.xpath(("td/a[@title='Remove']"));


    public String getWishlistItemNameText(WebElement wishlistItem){
        return wishlistItem.findElement(wishlistItemText).getText();
    }

    public WebElement getWishlistItemAddToCartButton(WebElement wishlistItem){
        return wishlistItem.findElement(wishListItemAddToCartButton);
    }

    public WebElement getWishlistItemRemoveButton(WebElement wishlistItem){
        return wishlistItem.findElement(wishlistItemRemoveButton);
    }



}
