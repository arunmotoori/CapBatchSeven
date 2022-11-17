package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageobjects.HomePage;
import pageobjects.SearchPage;

public class SearchTest extends Base{
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		loadPropertiesFile();
		driver = openBrowser(prop.getProperty("browserName"));
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void searchForAValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductDetailsIntoSearchBoxField(prop.getProperty("validProduct"));
		SearchPage searchPage = homePage.clickOnSearchButton();
		Assert.assertFalse(searchPage.displayStatusOfValidProduct());
		
	}
	
	@Test(priority=2)
	public void searchWithAnInvalidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductDetailsIntoSearchBoxField(prop.getProperty("invalidProduct"));
		SearchPage searchPage = homePage.clickOnSearchButton();
		Assert.assertNotEquals(searchPage.retrieveNoProductMatchesMessage(),prop.getProperty("noProductMatchesMessage"));
		
	}
	
	@Test(priority=3,dependsOnMethods={"searchForAValidProduct","searchWithAnInvalidProduct"})
	public void searchWithoutEnteringProductDetails() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductDetailsIntoSearchBoxField("");
		SearchPage searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMatchesMessage(),prop.getProperty("noProductMatchesMessage"));		
	
	}
	

}
