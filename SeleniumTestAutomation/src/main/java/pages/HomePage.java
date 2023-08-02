package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage{
	
	private WebDriver driver;
	
	public HomePage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='action more button']")
	private WebElement sNYogaButton;
	
	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	private WebElement signinLink;
	
	@FindBy(xpath = "//div[@class='panel header']//a[normalize-space()='Create an Account']")
	private WebElement signupLink;	
	
	public boolean sNYogaButtonValidation() throws InterruptedException {
		explicitWaitForElement(sNYogaButton);
		sNYogaButton.isDisplayed();
		// Specially failed this scenario as negative test case
		return false;
	}
	
	public boolean signinLinkValidation() throws InterruptedException {
		explicitWaitForElement(signinLink);
		signinLink.isDisplayed();
		return true;
	}
	
	public boolean signupLinkValidation() throws InterruptedException {
		explicitWaitForElement(signupLink);
		signupLink.isDisplayed();
		return true;
	}
	
	public void clickOnSignupLink() {
		explicitWaitForElement(signupLink);
		signupLink.click();
	}
	
	public void clickOnSigninLink() {
		explicitWaitForElement(signinLink);
		signinLink.click();
	}
	
	public void clickOnSNYogaButton() {
		explicitWaitForElement(sNYogaButton);
		sNYogaButton.click();
	}
}
