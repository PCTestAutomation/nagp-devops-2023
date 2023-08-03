package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class PaymentPage extends BasePage{
	
	private WebDriver driver;
	
	public PaymentPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Place Order']")
	private WebElement placeOrderButton;
	
	@FindBy(xpath = "//span[normalize-space()='Continue Shopping']")
	private WebElement continueShoppingButton;
	
	public boolean contShoppingValidation() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(continueShoppingButton);
		continueShoppingButton.isDisplayed();
		return true;
	}
	
	public void clickOnPlaceOrderButton() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(placeOrderButton);
		placeOrderButton.click();
	}
}