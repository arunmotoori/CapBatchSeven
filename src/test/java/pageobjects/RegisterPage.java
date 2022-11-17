package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(className="btn-primary")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@value='1'][@name='newsletter']")
	private WebElement yesRadioButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarning;
	
	
	public String retrieveDuplicateEmailWarningMessage() {
		
		return duplicateEmailWarning.getText();
		
	}
	
	public String retrievePrivacyPolicyWarningMessage() {
		
		return privacyPolicyWarning.getText();
		
	}
	
	public String retrieveFirstNameWarningMessage() {
		
		return firstNameWarning.getText();
		
	}
	
	public String retrieveLastNameWarningMessage() {
		
		return lastNameWarning.getText();
		
	}
	
	public String retrieveEmailWarningMessage() {
		
		return emailWarning.getText();
		
	}
	
	public String retrieveTelephoneWarningMessage() {
		
		return telephoneWarning.getText();
		
	}
	
	public String retrievePasswordWarningMessage() {
		
		return passwordWarning.getText();
		
	}
	
	public void selectYesForNewsletter() {
		
		yesRadioButton.click();
		
	}
	
	public void enterFirstName(String firstNameText) {
		
		firstNameField.sendKeys(firstNameText);
		
	}
	
	public void enterLastName(String lastNameText) {
		
		lastNameField.sendKeys(lastNameText);
		
	}
	
	public void enterEmailAddress(String emailText) {
		
		emailField.sendKeys(emailText);
		
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		
		telephoneField.sendKeys(telephoneText);
		
	}
	
	public void enterPassword(String passwordText) {
		
		passwordField.sendKeys(passwordText);
		
	}
	
	public void enterConfirmPassword(String passwordText) {
		
		passwordConfirmField.sendKeys(passwordText);
		
	}
	
	public void selectPrivacyPolicy() {
		
		privacyPolicyField.click();
		
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}

}
