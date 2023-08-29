package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportsUtility;
import utilities.GlobalFunctions;
import utilities.ScreenShotUtility;

public class TestListener implements ITestListener {

    ExtentReports extentReports = ExtentReportsUtility.getExtentReportsObject();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started!");
        extentTest = extentReports.createTest(result.getName());
        threadLocal.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
       System.out.println("Test Passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
        WebDriver driver;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("mDriver").get(result.getInstance());
            ScreenShotUtility.saveScreenshot(driver,"reports",result.getMethod().getMethodName());

        }catch (Exception e){
            Assert.fail("error in getting screenshot during failed test case. "+e.getMessage());
        }

        threadLocal.get().addScreenCaptureFromPath(result.getMethod().getMethodName()+".png");
        // here we dont need to specify the file path as we have saved the screenshot in the reports folder
        //https://stackoverflow.com/questions/47555567/extentreports-screenshot-not-in-the-report-broken-image

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped!");
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Suite is ending!");
        extentReports.flush();
    }
}
