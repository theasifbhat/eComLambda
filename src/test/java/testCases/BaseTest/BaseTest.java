package testCases.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage.LandingPage;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {
    public WebDriver mDriver;
    public LandingPage landingPage;

    public WebDriver initialize(){
        WebDriver driver;
        String browserName = "";

        try {
            Properties properties = new Properties();
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
            properties.load(fs);
            browserName = properties.getProperty("browser") != null ? properties.getProperty("browser") : "chrome";
        }catch (Exception e){
            System.out.println("Exception in loading values from properties file " +e.getMessage());
        }

        switch (browserName) {
            case "firefox" -> {
                driver = new FirefoxDriver();
                System.out.println("Browser is firefox");
            }
            case "safari" -> {
                driver = new SafariDriver();
                System.out.println("Browser is Safari");
            }
            default -> {
                driver = new ChromeDriver();
                System.out.println("Browser is Chrome");
            }
        }

        driver.manage().window().maximize();
        return driver;

    }


    @BeforeMethod
    public void launchApplication(){
        mDriver= initialize();
        landingPage = new LandingPage(mDriver);
        landingPage.goToLandingPage();
    }

   @AfterMethod
    public void tearDown() {
        try {
            if (mDriver != null) {
                mDriver.quit();
                System.out.println("Driver is closed ");
            } else {
                System.out.println("Driver is null ");
            }
        } catch (Exception e) {
            // Log or handle the exception appropriately
            System.out.println("Exception occurred while closing the browser: " + e.getMessage());
        }

    }
}
