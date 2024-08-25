package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import dsalgoTests.dsalgoTest;
import utils.ScreenshotUtil;

public class ExtentReportListener implements ITestListener  {

	    private ExtentReports extent;
	    private ExtentTest test;

	    @Override
	    public void onStart(ITestContext context) {
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/extent-report.html");
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, result.getThrowable());
	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, result.getThrowable());

	        WebDriver driver = ((dsalgoTest) result.getInstance()).getDriver();
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        
	        // Create a unique screenshot file name with timestamp
	        String screenshotName = result.getMethod().getMethodName() + "_" + timestamp + ".png";
	        String screenshotPath = "screenshots/" + screenshotName;
	        
	        // Capture the screenshot and save it
	        ScreenshotUtil.captureScreenshot(driver, screenshotPath);
	        
	        // Add screenshot to the test report
	        test.addScreenCaptureFromPath(screenshotPath);
	       
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.log(Status.SKIP, "Test Skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
	}

	
	

