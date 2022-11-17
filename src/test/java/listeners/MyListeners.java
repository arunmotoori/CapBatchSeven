package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import utils.ExtentReporter;

public class MyListeners extends Base implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		
		String testMethodName = result.getName();
		extentTest = extentReport.createTest(testMethodName);
		extentTest.log(Status.INFO,testMethodName+" started executing");
				
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testMethodName = result.getName();
		extentTest.log(Status.PASS,testMethodName+" got passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testMethodName = result.getName();
		
		WebDriver driver = null;
		
		//Code for taking the Screenshot
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String screenshotPath = captureScreenshot(driver,testMethodName);
		extentTest.addScreenCaptureFromPath(screenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testMethodName+" got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testMethodName = result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, testMethodName+" got skipped");
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		try {
			extentReport = ExtentReporter.getExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\Reports\\extentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
