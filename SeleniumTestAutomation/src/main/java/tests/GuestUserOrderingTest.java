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
import pages.MenPage;
import pages.PaymentPage;
import pages.WomenPage;
import utilities.Common;
import utilities.PropertyFileReader;

public class GuestUserOrderingTest {
	HomePage homePage;
	LoginPage loginPage;
	WomenPage womenPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	PaymentPage paymentPage;
	MenPage menPage;
	Common common;
	private static final Logger logger = BasePage.initializeLog4JEvents();

	public GuestUserOrderingTest() {
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

	@Test(dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
	public void testGuestUserOrdering(String Size, String Color, String Material) throws IOException, InterruptedException {
		homePage = new HomePage();
		loginPage = new LoginPage();
		womenPage = new WomenPage();
		cartPage = new CartPage();
		checkoutPage = new CheckoutPage();
		paymentPage = new PaymentPage();
		menPage = new MenPage();
		ExtentTest test = BasePage.extent.createTest("Guest User is placing an order.").assignAuthor("Prashant Chauhan")
				.assignCategory("Order Placement By Guest User Test Case").assignDevice("Windows 11")
				.info("Guest User is placing an order.")
				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
		if (womenPage.verifyWomenTab()) {
			try {
				logger.info("Clicking on Shop New Yoga button");
		        homePage.clickOnSNYogaButton();
		        logger.info("Filtering the size, color and pattern");
		        cartPage.selectSize(Size);
		        cartPage.selectColor(Color);
		        cartPage.selectMaterial(Material);
		        logger.info("Items adding into cart");
		        cartPage.addItemToCart();
				logger.info("Open the cart section");
				cartPage.clickONCartLink();
				logger.info("Proceeding for the checkout");
				cartPage.clickOnproceedToCheckoutButton();
				logger.info("Checking out the item");
				cartPage.checkoutTheGuestUserItem();
				checkoutPage.clickTableRateRadioButton();
				checkoutPage.clickOnNextButton();
				logger.info("Placing the order");
				paymentPage.clickOnPlaceOrderButton();
				logger.info("Verifying the order has been placed");
				paymentPage.contShoppingValidation();
				test.pass("Order placed successfully by guest user.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Guset_User_Order_Placed-PASSED")).build());
				logger.info("Log4j: Order placed successfully by guest user.");
			} catch (Exception e) {
				e.printStackTrace();
				test.fail("Guest user is unable to placed the order.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Guset_User_Place_Order-FAILED")).build());
				logger.error("Log4j: Guest user is unable to placed the order.");
			}
		} else {
			test.fail("Men tab is not displayed.", MediaEntityBuilder
					.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Men_Tab-FAILED")).build());
			logger.error("Log4j: Men tab is not displayed.");
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