package pageObjects.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage{

 WebDriver mDriver;
 WebDriverWait webDriverWait;


 public BasePage(WebDriver mDriver){
     this.mDriver= mDriver;
     webDriverWait = new WebDriverWait(mDriver, Duration.ofSeconds(7));
 }

 public void waitTillElementIsVisible(By locator){
     webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }

    public void waitTillElementVisibleUsingWebElement(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitTillElementIsPresent(By locator){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
