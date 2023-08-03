package tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import base.BasePage;
import base.DataProviderClass;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.WomenPage;
import utilities.Common;
import utilities.PropertyFileReader;

public class WomensItemsOrderingTest {
	HomePage homePage;
	LoginPage loginPage;
	WomenPage womenPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	PaymentPage paymentPage;
	Common common;
	private static final Logger logger = BasePage.initializeLog4JEvents();

	public WomensItemsOrderingTest() {
		logger.info("Moving the test results in archieve folder");
		BasePage.movingTestResultsInArchive();
		logger.info("Moving the screenshots in archieve folder");
		BasePage.movingScreenshotsInArchive();
	}

	@BeforeTest
	public void setUp() {
		logger.info("Instantiated the extent reports and other stuffs");
		BasePage.getInstance();
	}

	@BeforeMethod
	public void openUrl() throws InterruptedException {
		common = new Common();
		logger.info("Initializing the browser");
		BasePage.initializeBrowser(PropertyFileReader.getData("CURRENT_BROWSER"));
		logger.info("Navigating the application url");
		common.navigatToUrl(PropertyFileReader.getData("APPLICATION_URL"));
	}

	@Test(dataProvider = "getTestDataFromExcel", dataProviderClass = DataProviderClass.class)
	public void testWomenJacketsOrdering(String Size, String Color, String Material) throws IOException, InterruptedException {
		homePage = new HomePage();
		loginPage = new LoginPage();
		womenPage = new WomenPage();
		cartPage = new CartPage();
		checkoutPage = new CheckoutPage();
		paymentPage = new PaymentPage();
		ExtentTest test = BasePage.extent.createTest("Placing an order with women item.").assignAuthor("Prashant Chauhan")
				.assignCategory("Womens Items Ordering Test Case").assignDevice("Windows 11")
				.info("Placing an order while fetching the data from excel sheet.")
				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
		if (womenPage.verifyWomenTab()) {
			try {
				logger.info("Clicking on women's jacket option");
		        womenPage.clickOnWomenJacketOption();
		        logger.info("Filtering the size, color and pattern");
		        cartPage.selectSize(Size);
		        cartPage.selectColor(Color);
		        cartPage.selectMaterial(Material);
		        logger.info("Items adding into cart");
		        cartPage.addItemToCart();
		        logger.info("User going to sign in");
		        homePage.clickOnSigninLink();
				loginPage.enterUserEmail();
				loginPage.enterUserPassword();
				loginPage.clickOnSignInButton();
				logger.info("Open the cart section");
				cartPage.clickONCartLink();
				cartPage.clickOnproceedToCheckoutButton();
				logger.info("Checking out the item");
				cartPage.checkoutTheItem();
				checkoutPage.clickTableRateRadioButton();
				checkoutPage.clickOnNextButton();
				logger.info("Placing the order");
				paymentPage.clickOnPlaceOrderButton();
				logger.info("Verifying the order has been placed");
				paymentPage.contShoppingValidation();
				test.pass("Order placed successfully.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Women_Items_Order_Placed-PASSED")).build());
				logger.info("Log4j: Order placed successfully with women item.");
			} catch (Exception e) {
				e.printStackTrace();
				test.fail("Unable to placed the order with women item.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Women_Items_Place_Order-FAILED")).build());
				logger.error("Log4j: Unable to placed the order with women item.");
			}
		} else {
			test.fail("Women tab is not displayed.", MediaEntityBuilder
					.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Women_Tab-FAILED")).build());
			logger.error("Log4j: Women tab is not displayed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		BasePage.driver.close();
	}

	@AfterTest
	public void flushingReports() throws IOException {
		BasePage.extent.flush();
		Desktop.getDesktop().browse(new File(BasePage.reportPath).toURI());
	}
}