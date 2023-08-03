package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class WomenPage extends BasePage{
	
	private WebDriver driver;
	
	public WomenPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Women']")
	private WebElement womenTab;
	
	@FindBy(xpath = "//a[@id='ui-id-9']//span[contains(text(),'Tops')]")
	private WebElement topsDropdownOption;
	
	@FindBy(xpath = "//a[@id='ui-id-11']//span[contains(text(),'Jackets')]")
	private WebElement jacketDropdownOption;	
	
	public void clickOnWomenJacketOption() {
		explicitWaitForElement(womenTab);
		Actions actions = new Actions(driver);
		actions.moveToElement(womenTab).moveToElement(topsDropdownOption).moveToElement(jacketDropdownOption).build().perform();
		jacketDropdownOption.click();
	}
	
	public boolean verifyWomenTab() {
		womenTab.isDisplayed();
		return true;
	}
}
