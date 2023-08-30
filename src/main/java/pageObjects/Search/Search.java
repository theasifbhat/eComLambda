package pageObjects.Search;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    private WebElement searchResultContainer;

    @FindBy(xpath = "//div[@id='entry_212469']//p")
    private WebElement noResultText;
    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    private List<WebElement> searchResultItemName;
    @FindBy(id = "input-search")
    private WebElement searchBox;
    @FindBy(id = "button-search")
    private WebElement searchButton;

    @FindBy(id = "description")
    private WebElement searchInDescriptionCheckcbox;

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
       if(!searchInDescriptionCheckcbox.isSelected()){
           Actions actions = new Actions(mDriver);
           actions.moveToElement(searchInDescriptionCheckcbox).click().build().perform();
       }
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



}
