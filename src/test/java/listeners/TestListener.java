package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
       System.out.println("Test Suite started!");
    }
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started!");
    }

    public void onTestSuccess(ITestResult result) {
       System.out.println("Test Passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
    }
}
