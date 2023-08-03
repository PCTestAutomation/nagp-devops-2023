package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class CheckoutPage extends BasePage{
	
	private WebDriver driver;
	
	public CheckoutPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@value='flatrate_flatrate']")
	private WebElement tableRateRadioButton;
	
	@FindBy(xpath = "//span[normalize-space()='Next']")
	private WebElement nextButton;
	
	public void clickTableRateRadioButton() throws InterruptedException {
		explicitWaitForElement(tableRateRadioButton);
		tableRateRadioButton.click();
	}
	
	public void clickOnNextButton() {
		explicitWaitForElement(nextButton);
		nextButton.click();
	}
}
