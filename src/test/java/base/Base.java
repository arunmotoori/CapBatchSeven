package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class Base {
	
	public Properties prop;
	
	public void loadPropertiesFile() throws IOException {
		
		prop = new Properties();
		
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties");
		
		FileInputStream fis = new FileInputStream(propFile);
		
		prop.load(fis);
		
	}
	
	public WebDriver openBrowser(String browserName){
		
		WebDriver driver = null;
		
		if(browserName.equals("chrome")) {
			
			driver = new ChromeDriver();
			
		}else if(browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equals("edge")) {
			
			driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
		
		
	}
	
	public String generateTimeStamp() {
		
		Date date = new Date();
		
		return date.toString().replace(" ","_").replace(":","_");
		
	}
	
	public String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		File destScreenshotFile = new File(destFilePath);
		try {
			FileHandler.copy(srcScreenshotFile, destScreenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFilePath;
	}
	
	

}
