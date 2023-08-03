package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;
import utilities.PropertyFileReader;

public class CartPage extends BasePage{
	
	private WebDriver driver;
	
	public CartPage() {
		this.driver = BasePage.driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath = "//a[@class='action showcart']")
	private WebElement emptyCartLink;
	
	@FindBy(xpath = "//span[@class='counter-number']")
	private WebElement cartLink;
	
	@FindBy(xpath = "//strong[@class='subtitle empty']")
	private WebElement emptyCartMessage;
	
	@FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
	private WebElement proceedToCheckoutButton;
	
	@FindBy(xpath = "//div[@class='control _with-tooltip']//input[@id='customer-email']")
	private WebElement emailAddressTB;
	
	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement firstNameTB;
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastNameTB;	
	
	@FindBy(xpath = "//input[@name='street[0]']")
	private WebElement streetAddressTB;
	
	@FindBy(xpath = "//input[@name='city']")
	private WebElement cityTB;	
	
	@FindBy(xpath = "//select[@name='region_id']")
	private WebElement stateDropDown;
	
	@FindBy(xpath = "//input[@name='postcode']")
	private WebElement zipCodeTB;
	
	@FindBy(xpath = "//select[@name='country_id']")
	private WebElement countryDropdown;
	
	@FindBy(xpath = "//input[@name='telephone']")
	private WebElement phoneNumberTB;
	
	@FindBy(xpath = "//input[@name='ko_unique_1']")
	private WebElement shippingMethodRadioButton;
	
	@FindBy(xpath = "//div[@class='shipping-address-item selected-item']")
	private WebElement OldShippingAddress;
	
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
	
	public void clickONCartLink() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(cartLink);
		cartLink.click();
	}
	
	public void clickOnproceedToCheckoutButton() throws InterruptedException {
		explicitWaitForElement(proceedToCheckoutButton);
		Thread.sleep(3000);
		proceedToCheckoutButton.click();
		Thread.sleep(5000);
	}
	
	public void sendEmailAddress() {
		emailAddressTB.clear();
		emailAddressTB.sendKeys(PropertyFileReader.getData("GUEST_USER_EMAIL"));
	}
	
	public void sendFirstName() {
		firstNameTB.clear();
		firstNameTB.sendKeys(PropertyFileReader.getData("USER_FNAME"));
	}
	
	public void sendLastName() {
		lastNameTB.clear();
		lastNameTB.sendKeys(PropertyFileReader.getData("USER_LNAME"));
	}
	
	public void sendStreetAddress() throws InterruptedException {
		explicitWaitForElement(streetAddressTB);
		streetAddressTB.clear();
		streetAddressTB.sendKeys(PropertyFileReader.getData("STREET_ADDRESS"));
	}
	
	public void selectState() {
		explicitWaitForElement(stateDropDown);
		Select state = new Select(stateDropDown);
		state.selectByVisibleText(PropertyFileReader.getData("USER_STATE"));
	}
	
	public void sendCity() {
		cityTB.clear();
		cityTB.sendKeys(PropertyFileReader.getData("USER_CITY"));
	}
	
	public void selectCountry() {
		explicitWaitForElement(stateDropDown);
		Select country = new Select(stateDropDown);
		country.selectByValue(PropertyFileReader.getData("USER_COUNTRY"));
	}
	
	public void sendZipCode() {
		zipCodeTB.click();
		zipCodeTB.clear();
		zipCodeTB.sendKeys(PropertyFileReader.getData("USER_ZIPCODE"));
	}
	
	public void sendPhoneNumber() {
		phoneNumberTB.clear();
		phoneNumberTB.sendKeys(PropertyFileReader.getData("USER_PHONENUMBER"));
	}
	
	public boolean verifyPreviousAddress() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(OldShippingAddress);
		OldShippingAddress.isDisplayed();
		return true;
	}
	
	public void checkoutTheItem() throws InterruptedException {
		if (verifyPreviousAddress()) {
		}		
	}
	
	public void checkoutTheGuestUserItem() throws InterruptedException {
		if (emailAddressTB.isDisplayed() && emailAddressTB.isEnabled()) {
			sendEmailAddress();
			sendFirstName();
			sendLastName();
			sendStreetAddress();
			sendCity();
			selectState();
			sendZipCode();
			sendPhoneNumber();
		}		
	}
	
	public void selectSize(String dynamicValue) {
		explicitWaitForElement(sizeMenu);
		sizeMenu.click();
		By dynamicLocator = By.xpath("//a[@aria-label='" + dynamicValue + "']//div[contains(@class,'swatch-option text')][normalize-space()='" + dynamicValue + "']");
        sizeOption = driver.findElement(dynamicLocator);
		sizeOption.click();
	}
	
	public void selectColor(String dynamicValue) {
		explicitWaitForElement(colorMenu);
		colorMenu.click();
		By dynamicLocator = By.xpath("//a[@aria-label='" + dynamicValue + "']//div[contains(@class,'swatch-option color')]");
        colorOption = driver.findElement(dynamicLocator);
        colorOption.click();
	}
	
	public void selectMaterial(String dynamicValue) throws InterruptedException {
		explicitWaitForElement(materialMenu);
		Thread.sleep(3000);
		materialMenu.click();
		By dynamicLocator = By.xpath("//div[@id='narrow-by-list']//div[@class='filter-options-content']//li/a[contains(text(),'" + dynamicValue + "')]");
        materialOption = driver.findElement(dynamicLocator);
        materialOption.click();
	}
	
	public void addItemToCart() throws InterruptedException {
		explicitWaitForElement(firstItem);
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(firstItem).build().perform();
		addToCartButton.click();
	}
	
	public void clickOnEmptyCartLink() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(emptyCartLink);
		emptyCartLink.click();
	}
	
	public boolean verifyEmptyCartMessage() throws InterruptedException {
		Thread.sleep(5000);
		explicitWaitForElement(emptyCartMessage);
		emptyCartMessage.isDisplayed();
		return true;
	}
}