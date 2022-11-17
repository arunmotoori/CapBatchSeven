package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageobjects.AccountSuccessPage;
import pageobjects.HomePage;
import pageobjects.RegisterPage;

//This is RegisterTest

public class RegisterTest extends Base{
	
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
	
	
	@Test(priority=1)
	public void registerWithMandatoryFields() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		RegisterPage registerPage = homePage.selectRegisterOption();
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmailAddress("amotooricap9"+generateTimeStamp()+"@gmail.com");
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertEquals(accountSuccessPage.retrieveAccountCreatedHeading(),prop.getProperty("accountCreationSuccessMessage"));
		
	}
	
	@Test(priority=2)
	public void registerWithAllFields() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		RegisterPage registerPage = homePage.selectRegisterOption();
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmailAddress("amotooricap9"+generateTimeStamp()+"@gmail.com");
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesForNewsletter();
		registerPage.selectPrivacyPolicy();
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertEquals(accountSuccessPage.retrieveAccountCreatedHeading(),prop.getProperty("accountCreationSuccessMessage"));
		
	}
	
	@Test(priority=3)
	public void registerWithoutFillingAnyFieds() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		RegisterPage registerPage = homePage.selectRegisterOption();
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarningMessage().contains(prop.getProperty("privacyPolicyWarning")));
		Assert.assertEquals(registerPage.retrieveFirstNameWarningMessage(),prop.getProperty("firstNameWarning"));
		Assert.assertEquals(registerPage.retrieveLastNameWarningMessage(),prop.getProperty("lastNameWarning"));
		Assert.assertEquals(registerPage.retrieveEmailWarningMessage(),prop.getProperty("emailWarning"));
		Assert.assertEquals(registerPage.retrieveTelephoneWarningMessage(),prop.getProperty("telephoneWarning"));
		Assert.assertEquals(registerPage.retrievePasswordWarningMessage(),prop.getProperty("passwordWarning"));
		
	}
	
	@Test(priority=4)
	public void registerWithDuplicateEmailAddress() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		RegisterPage registerPage = homePage.selectRegisterOption();
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmailOne"));
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.retrieveDuplicateEmailWarningMessage().contains(prop.getProperty("duplicateEmailWarning")));
		
	}
	

}
