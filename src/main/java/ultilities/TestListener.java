package ultilities;

import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import factory.DriverFactory;
import factory.ExtentFactory;

public class TestListener implements ITestListener {

	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	
	@Override
	public void onTestStart(ITestResult result) {
		createTest(result);
	}
	
	private void createTest(ITestResult result) {
		Capabilities browserCap = ((RemoteWebDriver) DriverFactory.getInstance().getDriver()).getCapabilities();

		String browserName = browserCap.getBrowserName();
        String methodName = result.getMethod().getMethodName();
        extentTest = extentReport.createTest(methodName).assignCategory(browserName);
        ExtentFactory.getInstance().setExtent(extentTest);
        ExtentFactory.getInstance().getExtent().info("Starting with browser: " + browserName.toUpperCase());
    }

	@Override
	public void onTestSuccess(ITestResult result) {
		// Add log to html report when test case is pass
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case is Passed");
		ExtentFactory.getInstance().removeExtent();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Add log to  html report when test case is failure
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case is Fail");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		
		// Add screenshot for failed test
		try {
			extentTest.addScreenCaptureFromPath(TestUtilities.captureScreenshot(result.getName()), "Test case failure screenshot");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentFactory.getInstance().removeExtent();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Add log to html report when test case is skip
		ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " is Skipped");
		ExtentFactory.getInstance().removeExtent();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// Start all method before test
		try {
			extentReport = new ExtentReportManager().setupExtentReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		// Flush to html report
		extentReport.flush();
	}
}
