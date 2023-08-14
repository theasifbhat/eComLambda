package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;

public class ScreenShotUtility {

public static void saveScreenshot(WebDriver mDriver,String packageName, String fileName){
    TakesScreenshot takesScreenshot = (TakesScreenshot) mDriver;
    File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
    File destination = new File(System.getProperty("user.dir")+"/"+packageName+"/"+fileName+".png");
    try {
        Files.copy(source.toPath(), destination.toPath());
    }catch (Exception e){
        Assert.fail("failed to copy screenshot "+e.getMessage());
    }
}

}
