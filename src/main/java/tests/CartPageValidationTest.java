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
import pages.CartPage;
import pages.HomePage;
import utilities.Common;
import utilities.PropertyFileReader;

public class CartPageValidationTest {
	HomePage homePage;
	CartPage cartPage;
	Common common;
	private static final Logger logger = BasePage.initializeLog4JEvents();

	public CartPageValidationTest() {
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

	@Test(groups={"high"})
	public void emptyCartValidation() throws Exception {
		cartPage = new CartPage();
		homePage = new HomePage();
		ExtentTest test = BasePage.extent.createTest("Empty Cart Validation Scenario.").assignAuthor("Prashant Chauhan")
				.assignCategory("Cart Page Validation Test Case").assignDevice("Windows 11")
				.info("User doing Empty Cart Validation.")
				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
		if (homePage.signupLinkValidation()) {
			try {
				logger.info("Clicking on cart link");
				cartPage.clickOnEmptyCartLink();
				logger.info("Verifying empty cart message");
				cartPage.verifyEmptyCartMessage();
				test.pass("Empty cart message shown.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Empty_Cart-PASSED")).build());
				logger.info("Log4j: Empty cart message shown.");
			} catch (Exception e) {
				e.printStackTrace();
				test.fail("Empty cart message not shown.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Empty_Cart-FAILED")).build());
				logger.error("Log4j: Empty cart message not shown.");
			}
		} else {
			test.fail("Empty cart message not shown.", MediaEntityBuilder
					.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Empty_Cart-FAILED")).build());
			logger.error("Log4j: Empty cart message not shown.");
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