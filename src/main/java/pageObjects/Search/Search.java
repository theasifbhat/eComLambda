package pageObjects.Search;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
    private WebElement searchResultContainer;

    @FindBy(xpath = "//div[@id='entry_212469']//p")
    private WebElement noResultText;
    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    private List<WebElement> searchResultItemName;

    @Getter
    @FindBy(xpath = "//div[@class='product-thumb']//span[@class='price-new']")
    private List<WebElement> searchResultItemPrice;

    @FindBy(id = "input-search")
    private WebElement searchBox;
    @FindBy(id = "button-search")
    private WebElement searchButton;

    @FindBy(id = "description")
    private WebElement searchInDescriptionCheckbox;

    @Getter
    @FindBy(xpath = "//div[@id='entry_212469']//div[@class='product-thumb-top']//a")
    private List<WebElement> searchResultItems;

    @Getter
    @FindBy(xpath = "//div[@id='entry_212456']/h1")
    private WebElement searchQueryLabel;

    @Getter
    @FindBy(xpath = "//div[@id='entry_212469']/div/div")
    private List<WebElement> searchResultItemsContainers;

    @FindBy(id = "list-view")
    private WebElement listViewButton;

    @FindBy(id = "grid-view")
    private WebElement gridViewButton;

    @FindBy(id = "input-sort-212464")
    private WebElement sortSelect;



    //locators for actions on result items

    By addToCartAction = By.xpath("parent::div/following-sibling::div/button[@title='Add to Cart']");
    By addToWishlistAction = By.xpath("parent::div/following-sibling::div/button[@title='Add to Wish List']");



    //setter methods

    public void setSearchBoxText(String text){
        searchBox.sendKeys(text);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void clickOnListView(){
        listViewButton.click();
    }

    public void clickOnGridView(){
        gridViewButton.click();
    }

    public void setSearchInDescriptionCheckboxToTrue(){
       if(!searchInDescriptionCheckbox.isSelected()){
           Actions actions = new Actions(mDriver);
           actions.moveToElement(searchInDescriptionCheckbox).click().build().perform();
       }
    }

    public void selectSortFromSortDropdown(int index){
        // 0 for default, 1 for Best Seller, 2 for Popular, 3 for newest, 4 for Name A-Z, 5 for Z-A, 6 for Price low to high,
        // 7 for price high to low, 8 for rating highest, 9 for lowest first, 10 for Model a-z, 11 for model z-a

        Select select = new Select(sortSelect);
        select.selectByIndex(index);
    }




    //complex methods for oprations
    public List<String> getSearchResultNames(){
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        List<String> items = new ArrayList<>();
        searchResultItemName.forEach(it->{
            items.add(it.getText());
        });
        return items;
    }

    public List<WebElement> getSearchResultWebElements(){
        waitTillElementsAreVisibleUsingWebElement(searchResultItems);
        return searchResultItems;

    }

    public boolean searchProductsWithNonExistingProductName(){   //returns true if label is found else false
        waitTillElementIsVisibleUsingWebElement(searchResultContainer);
        return Objects.equals(noResultText.getText(), "There is no product that matches the search criteria.");
    }

    public String getSearchItemsContainerClass(){    // all the items have the same class
        return searchResultItemsContainers.get(0).getAttribute("class");
    }


    //getting actions on search item results (like addToCart,addToWishlist )


    //takes the argument of a search result from search page
    public WebElement getAddToCartActionButton(WebElement searchResultItem){
        return searchResultItem.findElement(addToCartAction);
    }

    public WebElement getAddToWishlistActionButton(WebElement searchResultItem){
        return searchResultItem.findElement(addToWishlistAction);
    }


}
