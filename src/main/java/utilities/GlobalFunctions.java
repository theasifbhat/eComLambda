package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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

public static String getPropertyFromPropertyFile(String key){

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

    public static void updatePropertyFromProperty(String key, String value) {
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
            properties.load(inputStream);
            inputStream.close();

            properties.setProperty(key, value);

            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (Exception exception) {
            Assert.fail("Exception in updating property file " + exception.getMessage());
        }
    }


public static String generateRandomString(){
    return ""+System.currentTimeMillis();
}

public static List<Float> convertListOfStringOfPricesToListOfFloat(List<String> beforeList){
    List<Float> convertedPriceFromString = new ArrayList<>();
    for (String price: beforeList){

        price = price.replace("$","");

        if(!price.contains(",")){
            convertedPriceFromString.add(Float.parseFloat(price));
        }
        else{
            convertedPriceFromString.add(Float.parseFloat(price.replace(",","")));
        }
    }
    return convertedPriceFromString;
}

public static String sanitizeImageUrl(String unSanitizedImageUrl){
    String[] first = unSanitizedImageUrl.split("product/");
    //0 index contains url till megastore-2/
    //1 contains filename.webp
    String secondPart = first[1].split("-")[0];
    return first[0]+"product/"+secondPart;
}

public static void scrollIntoView(WebDriver mDriver, WebElement webElement){
    JavascriptExecutor executor = (JavascriptExecutor) mDriver;
    executor.executeScript("arguments[0].scrollIntoView();", webElement);
}



}
