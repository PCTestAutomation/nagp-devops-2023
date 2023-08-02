package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class MenPage extends BasePage{
	
	private WebDriver driver;
	
	public MenPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Men']")
	private WebElement menTab;
	
	@FindBy(xpath = "//a[@id='ui-id-18']//span[contains(text(),'Bottoms')]")
	private WebElement bottomsDropdownOption;
	
	@FindBy(xpath = "//a[@id='ui-id-24']//span[contains(text(),'Shorts')]")
	private WebElement shortsDropdownOption;
	
	@FindBy(xpath = "//div[normalize-space()='Size']")
	private WebElement sizeMenu;
	
	@FindBy(xpath = "//a[@aria-label='$']//div[contains(@class,'swatch-option text')][normalize-space()='$']")
	private WebElement sizeOption;
	
	@FindBy(xpath = "(//img[@class='product-image-photo'])[1]")
	private WebElement firstItem;
	
	@FindBy(xpath = "(//button[@title='Add to Cart'])[1]")
	private WebElement addToCartButton;
	
	public void dynamicSize(WebDriver driver, String dynamicValue) {
        //PageFactory.initElements(driver, this);
        By dynamicLocator = By.xpath("//a[@aria-label='" + dynamicValue + "']//div[contains(@class,'swatch-option text')][normalize-space()='" + dynamicValue + "']");
        sizeOption = driver.findElement(dynamicLocator);
    }
	
	@FindBy(xpath = "//div[normalize-space()='Color']")
	private WebElement colorMenu;
	
	@FindBy(xpath = "//a[@aria-label='$']//div[contains(@class,'swatch-option color')]")
	private WebElement colorOption;
	
	public void dynamicColor(WebDriver driver, String dynamicValue) {
        //PageFactory.initElements(driver, this);
        By dynamicLocator = By.xpath("//a[@aria-label='" + dynamicValue + "']//div[contains(@class,'swatch-option color')]");
        colorOption = driver.findElement(dynamicLocator);
    }
	
	@FindBy(xpath = "//div[normalize-space()='Material']")
	private WebElement materialMenu;
	
	@FindBy(xpath = "//div[@id='narrow-by-list']//div[@class='filter-options-content']//li/a[contains(text(),'$')]")
	private WebElement materialOption;
	
	public void dynamicMaterial(WebDriver driver, String dynamicValue) {
        //PageFactory.initElements(driver, this);
        By dynamicLocator = By.xpath("//div[@id='narrow-by-list']//div[@class='filter-options-content']//li/a[contains(text(),'" + dynamicValue + "')]");
        materialOption = driver.findElement(dynamicLocator);
    }
	
	public void clickOnMenShortsOption() {
		explicitWaitForElement(menTab);
		Actions actions = new Actions(driver);
		actions.moveToElement(menTab).moveToElement(bottomsDropdownOption).moveToElement(shortsDropdownOption).build().perform();
		shortsDropdownOption.click();
	}
	
	public boolean verifyMenTab() {
		menTab.isDisplayed();
		return true;
	}
}
