package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports getExtentReport() throws IOException {
		
		ExtentReports extentReport = new ExtentReports();
		
		File report = new File(System.getProperty("user.dir")+"\\Reports\\extentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(report);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Results");
		sparkReporter.config().setTimeStampFormat("dd:MM:yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties");
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		
		extentReport.setSystemInfo("Application URL",prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name",prop.getProperty("browserName"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java version",System.getProperty("java.version"));
		extentReport.setSystemInfo("Email Address",prop.getProperty("validEmailOne"));
		extentReport.setSystemInfo("Password",prop.getProperty("validPassword"));
		
		return extentReport;
		
	}

}
