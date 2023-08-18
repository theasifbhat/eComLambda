package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtility {

public static ExtentReports getExtentReportsObject(){
    ExtentReports extentReports= new ExtentReports();
    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//extentReport.html");
    extentSparkReporter.config().setReportName("Automation Report");
    extentSparkReporter.config().setDocumentTitle("Automation Test Results");
    extentReports.setSystemInfo("Tester",GlobalFunctions.getPropertyFromPropertyFile("tester"));
    extentReports.setSystemInfo("Url",GlobalFunctions.getPropertyFromPropertyFile("url"));
    extentReports.setSystemInfo("Browser",GlobalFunctions.getPropertyFromPropertyFile("browser"));
    extentReports.setSystemInfo("OS",System.getProperty("os.name"));
    extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
    extentReports.attachReporter(extentSparkReporter);
    return extentReports;
}


}
