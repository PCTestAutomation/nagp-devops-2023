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
import pages.HomePage;
import pages.LoginPage;
import utilities.Common;
import utilities.PropertyFileReader;

public class LoginPageTest {
	HomePage homePage;
	LoginPage loginPage;
	Common common;
	private static final Logger logger = BasePage.initializeLog4JEvents();

	public LoginPageTest() {
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
	public void userSignUpScenario() throws Exception {
		homePage = new HomePage();
		loginPage = new LoginPage();
		ExtentTest test = BasePage.extent.createTest("User Signed Up Scenario.").assignAuthor("Prashant Chauhan")
				.assignCategory("Registration Test Case").assignDevice("Windows 11")
				.info("User can signed up scuccessfully.")
				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
		if (homePage.signupLinkValidation()) {
			try {
				logger.info("Clicking on signup link");
				homePage.clickOnSignupLink();
				logger.info("Enter first name");
				loginPage.enterFirstName();
				logger.info("Enter last name");
				loginPage.enterLastName();
				logger.info("Enter dynamic email address");
				loginPage.enterEmailAddress();
				logger.info("Enter password");
				loginPage.enterPassword();
				logger.info("Confirming the password");
				loginPage.enterConfirmPassword();
				logger.info("Clicking on create an account button");
				loginPage.clickCreateAnAccountButton();
				logger.info("Verifying that registration is successfully completed");
				loginPage.verifyRegistrationMessage();
				test.pass("Observe that user is able to signed up scuccessfully.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Signed_Up-PASSED")).build());
				logger.info("Log4j: Observe that user is able to signed up scuccessfully.");
			} catch (Exception e) {
				e.printStackTrace();
				test.fail("User registration has been failed.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Signed_Up-FAILED")).build());
				logger.error("Log4j: User signed up scenario has been failed.");
			}
		} else {
			test.fail("Signup link is not visible.", MediaEntityBuilder
					.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Signed_Up-FAILED")).build());
			logger.error("Log4j: Signup link is not visible.");
		}
	}

	@Test(groups={"low"})
	public void userLoggedInScenario() throws Exception {
		homePage = new HomePage();
		loginPage = new LoginPage();
		ExtentTest test = BasePage.extent.createTest("User Logged In Scenario.").assignAuthor("Prashant Chauhan")
				.assignCategory("LOGIN Test Case").assignDevice("Windows 11")
				.info("User can logged in scuccessfully.")
				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
		if (homePage.signinLinkValidation()) {
			try {
				logger.info("Clicking on signin link");
				homePage.clickOnSigninLink();
				logger.info("Enter email address");
				loginPage.enterUserEmail();
				logger.info("Enter password");
				loginPage.enterUserPassword();
				logger.info("Clicking on signin button");
				loginPage.clickOnSignInButton();
				logger.info("Verifying the welcome message");
				loginPage.verifyWelcomeMessage();
				test.pass("Observe that user is able to logged in scuccessfully.", MediaEntityBuilder
						.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Sign_In-PASSED")).build());
				logger.info("Log4j: Observe that user is able to logged in scuccessfully.");
			} catch (Exception e) {
				e.printStackTrace();
				test.fail("User login scenario has been failed.",
						MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Sign_In-FAILED")).build());
				logger.error("Log4j: User login scenario has been failed.");
			}
		} else {
			test.fail("Signin link is not visible.",
					MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("User_Sign_In-FAILED")).build());
			logger.error("Log4j: Signin link is not visible.");
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