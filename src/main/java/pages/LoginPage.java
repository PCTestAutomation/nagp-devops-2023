package pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import utilities.PropertyFileReader;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	
	public LoginPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='email_address']")
	private WebElement emailTB;
	
	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstNameTB;
	
	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement lastNameTB;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordTB;
	
	@FindBy(xpath = "//input[@id='password-confirmation']")
	private WebElement confirmPasswordTB;
	
	@FindBy(xpath = "//button[@title='Create an Account']")
	private WebElement createAnAccountBtn;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement userEmailTB;
	
	@FindBy(xpath = "//fieldset[@class='fieldset login']//input[@id='pass']")
	private WebElement userPasswordTB;
	
	@FindBy(xpath = "//fieldset[@class='fieldset login']//button[@id='send2']")
	private WebElement signINBtn;
	
	@FindBy(xpath = "//div[@role='alert']/div/div[normalize-space()='Thank you for registering with Main Website Store.']")
	private WebElement userRegistration;
	
	@FindBy(xpath = "//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, Prashant Chauhan!']")
	private WebElement welcomeMessage;
	
	@FindBy(xpath = "//div[@class='page messages']//div[contains(text(),'Thank you for registering with Main Website Store.')]")
	private WebElement registrationSuccessMessage;
	
	
	public void enterFirstName() {
		firstNameTB.clear();
		firstNameTB.sendKeys(PropertyFileReader.getData("USER_FNAME"));
	}
	
	public void enterLastName() {
		lastNameTB.clear();
		lastNameTB.sendKeys(PropertyFileReader.getData("USER_LNAME"));
	}
	
	public void enterEmailAddress() throws InterruptedException {
		emailTB.clear();
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
		emailTB.sendKeys("1feb1986+" + number + "@gmail.com");
		Thread.sleep(2000);
	}
	
	public void enterPassword() {
		passwordTB.clear();
		passwordTB.sendKeys(PropertyFileReader.getData("USER_PASSWORD"));
	}
	
	public void enterConfirmPassword() {
		confirmPasswordTB.clear();
		confirmPasswordTB.sendKeys(PropertyFileReader.getData("USER_CONFIRM_PASSWORD"));
	}
	
	public void clickCreateAnAccountButton() throws InterruptedException {
		createAnAccountBtn.click();
		Thread.sleep(3000);
	}
	
	public void enterUserEmail() throws InterruptedException {
		userEmailTB.clear();
		userEmailTB.sendKeys(PropertyFileReader.getData("USER_EMAIL"));
		Thread.sleep(2000);
	}
	
	public void enterUserPassword() {
		userPasswordTB.clear();
		userPasswordTB.sendKeys(PropertyFileReader.getData("USER_PASSWORD"));
	}
	
	public void clickOnSignInButton() {
		signINBtn.click();
	}
	
	public void verifyUserRegistration() {
		explicitWaitForElement(userRegistration);
		userRegistration.isDisplayed();
	}
	
	public void verifyWelcomeMessage() {
		explicitWaitForElement(welcomeMessage);
		welcomeMessage.isDisplayed();
	}
	
	public void verifyRegistrationMessage() {
		explicitWaitForElement(registrationSuccessMessage);
		registrationSuccessMessage.isDisplayed();
	}
}