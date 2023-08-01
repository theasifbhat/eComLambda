package pageObjects.Search;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search extends BasePage {

    WebDriver mDriver;
    public Search(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
    }

    @FindBy(css = "div[id='entry_212469']")
    WebElement searchResultContainer;

    @FindBy(xpath = "//div[@id='entry_212469']//p")
    WebElement noResultText;
    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    List<WebElement> searchResultItemName;



    public List<String> getSearchResultNames(){
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        List<String> items = new ArrayList<>();
        searchResultItemName.forEach(it->{
            items.add(it.getText());
        });
        return items;
    }

    public boolean searchProductsWithNonExistingProductName(){   //returns true if label is found else false
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        return Objects.equals(noResultText.getText(), "There is no product that matches the search criteria.");
    }



}
