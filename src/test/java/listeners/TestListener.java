package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportsUtility;
import utilities.GlobalFunctions;

public class TestListener implements ITestListener {

    ExtentReports extentReports = ExtentReportsUtility.getExtentReportsObject();
    ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started!");
        extentTest = extentReports.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
       System.out.println("Test Passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Failed!");
        extentTest.fail(result.getThrowable());
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
