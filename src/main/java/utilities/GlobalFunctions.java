package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class GlobalFunctions {

public static String generateRandomEmail() {
    return "theasifbhat+" + System.currentTimeMillis() + "@yopmail.com";
}

public static void waitForPageLoad(WebDriver mDriver){
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

public static String getPropertyFromPropertyFileWithKey(String key){

    String value = "";
    try{
    Properties properties = new Properties();
    FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
    properties.load(fs);
    value= properties.getProperty(key);
    }catch (Exception e){
        System.out.println("Exception in loading values from properties file " +e.getMessage());
    }
    return value;
}



}
