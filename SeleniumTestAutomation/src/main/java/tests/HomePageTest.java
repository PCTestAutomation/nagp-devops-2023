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
import utilities.Common;
import utilities.PropertyFileReader;

public class HomePageTest{
	HomePage homePage;
    Common common;
    private static final Logger logger = BasePage.initializeLog4JEvents();
    		
    public HomePageTest() {
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
    
    @Test(groups={"low"})
    public void verifyShopNewYogaButton() throws Exception {
    		homePage = new HomePage();    		
    		ExtentTest test = BasePage.extent
    				.createTest("Verifying Welcome label on Home page.")
    				.assignAuthor("Prashant Chauhan")
    				.assignCategory("Shop New Yoga Button Validation Test Case")
    				.assignDevice("Windows 11")
    				.info("Observe the Welcome label. Specially failed this scenario as a negative test case")
    				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
    		logger.info("Verifying Shop New Yoga Button on Home page");
    		if (homePage.sNYogaButtonValidation()) {
				test.pass("User can observe the Shop New Yoga Button.", MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Shop_New_Yoga_Button_Validation-PASSED")).build());
				logger.info("Log4j: User can observe the Shop New Yoga Button on Home page.");
			} else {
				test.fail("Unable to get the Shop New Yoga Button on Home page.", MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("Shop_New_Yoga_Button_Validation-FAILED")).build());
				logger.error("Log4j: Shop New Yoga Button validation failed.");
			}
    }

    @Test(groups={"high"})
    public void verifySignInLink() throws Exception {
    		homePage = new HomePage();
    		ExtentTest test = BasePage.extent
    				.createTest("Verifying SignIn link on Home page.")
    				.assignAuthor("Prashant Chauhan")
    				.assignCategory("Signin Link Validation Test Case")
    				.assignDevice("Windows 11")
    				.info("Observe the Signin link on Home page.")
    				.addScreenCaptureFromBase64String(BasePage.CaptureScreenshot());
    		logger.info("Verifying signin link on Home page");
			if (homePage.signinLinkValidation()) {
				test.pass("User can observe the Signin link.", MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("SignIn_Link_Validation-PASSED")).build());
				logger.info("Log4j: User can observe the Signin link on Home page.");
			} else {
				test.fail("Unable to get the Signin link on page.", MediaEntityBuilder.createScreenCaptureFromPath(BasePage.CaptureScreenshot("SignIn_Link_Validation-FAILED")).build());
				logger.error("Log4j: Signin link on Home page validation failed.");
			}
    }
    
    @AfterMethod
    public void tearDown() throws Exception  {
    	Thread.sleep(3000);
        BasePage.driver.close();
    }
 
    @AfterTest
    public void flushingReports() throws IOException {
    	BasePage.extent.flush();
    	Desktop.getDesktop().browse(new File(BasePage.reportPath).toURI());;
    }
}