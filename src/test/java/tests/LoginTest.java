package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.ExcelReader;

//This is LoginTest 

public class LoginTest extends Base{
	
	public WebDriver driver;
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		
		loadPropertiesFile();
		driver = openBrowser(prop.getProperty("browserName"));
		driver.get(prop.getProperty("url"));
		
	}

	@Test(priority=1,dataProvider="dataSupplierFromExcelFile")
	public void loginWithValidCredentials(String email,String password) {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		LoginPage loginPage = homePage.selectLoginOption();
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();		
		Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationLink());
	
	}
	
	@DataProvider
	public Object[][] dataSupplier1() {
		
		Object[][] data = {{prop.getProperty("validEmailOne"),prop.getProperty("validPassword")},
				{prop.getProperty("validEmailTwo"),prop.getProperty("validPassword")},
				{prop.getProperty("validEmailThree"),prop.getProperty("validPassword")}};
		
		return data;
		
	}
	
	@DataProvider
	public Object[][] dataSupplierFromExcelFile() throws IOException {
		
		Object[][] data = ExcelReader.readDataFromExcelFile();
		
		return data;
		
	}
	
	@Test(priority=2,dependsOnMethods={"loginWithValidCredentials"})
	public void loginWithInvalidEmailAndValidPassword() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		LoginPage loginPage = homePage.selectLoginOption();
		loginPage.enterEmailAddress("amotooricap9"+generateTimeStamp()+"@gmail.com");
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveTextOfEmailPasswordNotMatchingWarningMessage().contains(prop.getProperty("emailPasswordNoMatchWarning")));
		
	}
	
	@Test(priority=3,dependsOnMethods={"loginWithInvalidEmailAndValidPassword"})
	public void loginWithValidEmailAndInvalidPassword() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		LoginPage loginPage = homePage.selectLoginOption();
		loginPage.enterEmailAddress(prop.getProperty("validEmailOne"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveTextOfEmailPasswordNotMatchingWarningMessage().contains(prop.getProperty("emailPasswordNoMatchWarning")));
		
	}
	
	@Test(priority=4)
	public void loginWithInvalidCredentials() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		LoginPage loginPage = homePage.selectLoginOption();
		loginPage.enterEmailAddress("amotooricap9"+generateTimeStamp()+"@gmail.com");
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveTextOfEmailPasswordNotMatchingWarningMessage().contains(prop.getProperty("emailPasswordNoMatchWarning")));
		
	}
	
	@Test(priority=5)
	public void loginWithoutProvidingAnyCredentials() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		LoginPage loginPage = homePage.selectLoginOption();
		loginPage.enterEmailAddress("");
		loginPage.enterPassword("");
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveTextOfEmailPasswordNotMatchingWarningMessage().contains(prop.getProperty("emailPasswordNoMatchWarning")));
		
	}
	

}
