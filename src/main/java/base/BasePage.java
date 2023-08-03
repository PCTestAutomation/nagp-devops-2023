package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePage {

	public static WebDriver driver;
	public static ExtentReports extent;

	public static String fileName;
	public static String reportPath;

	static Logger logger = null;

	@Parameters({ "browser" })
	public static void initializeBrowser(String browser) {
		switch (browser) {
		case "chrome":
			logger.info("Initializing the chrome browser.");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("test-type");
			options.addArguments("enable-strict-powerful-feature-restrictions");
			options.addArguments("disable-geolocation");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			logger.info("Maximizing the chrome browser.");
			driver.manage().window().maximize();
			logger.info("Initializing the implicit wait for chrome browser.");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			break;
		case "firefox":
			logger.info("Initializing the firefox browser.");
			driver = new FirefoxDriver();
			logger.info("Maximizing the firefox browser.");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.info("Initializing the implicit wait for firefox browser.");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			break;
		case "ie":
			logger.info("Initializing the internet explorer browser.");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/src/main/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			logger.info("Maximizing the internet explorer browser.");
			driver.manage().window().maximize();
			logger.info("Initializing the implicit wait for internet explorer browser.");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			break;
		}
	}

	public static Logger initializeLog4JEvents() {
		// Initializing the log4j logger instances
		logger = Logger.getLogger(BasePage.class.getName());
		DOMConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\log4j.xml");
				//"C:\\Users\\prashantchauhan\\eclipse-workspace\\SeleniumTestAutomation\\src\\test\\resources\\log4j.xml");
		/*
		 * logger.debug("This is a debug message");
		 * logger.info("This is an info message");
		 * logger.warn("This is a warning message");
		 * logger.error("This is an error message");
		 * logger.fatal("This is a fatal message");
		 */
		return logger;
	}

	public static ExtentReports getInstance() {

		if (extent == null) {
			extent = new ExtentReports();
			Date date = new Date();
			fileName = date.toString().replace(":", "_").replace(" ", "_") + ".html";
			reportPath = System.getProperty("user.dir") + "\\Current Test Results\\" + fileName;
			System.out.println(reportPath);
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setDocumentTitle("NAGP Assignment Report");
			spark.config().setReportName("Test Automation Status");
			spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			spark.config().setTheme(Theme.DARK);
			extent.attachReporter(spark);
		}
		return extent;
	}

	public static String CaptureScreenshot() throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		String base64Code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		return base64Code;
	}

	public static String CaptureScreenshot(String fileName) throws IOException {
		Date date = new Date();
		String append = date.toString().replace(":", "_").replace(" ", "_") + ".jpeg";
		fileName = fileName + " " + append;
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\Current Screenshots\\" + fileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}

	public static void movingTestResultsInArchive() {
		// TODO code application logic here
		// File (or directory) to be moved
		File fileCurrent = new File(System.getProperty("user.dir") + "\\Current Test Results\\");
		// Destination directory
		File fileArchived = new File(System.getProperty("user.dir") + "\\Archived Test Results\\");
		// Check weather source exists and it is folder.
		if (fileCurrent.exists() && fileCurrent.isDirectory()) {
			// Get list of the files and iterate over them
			File[] listOfFiles = fileCurrent.listFiles();
			if (listOfFiles != null) {
				for (File child : listOfFiles) { // Move files to destination folder
					child.renameTo(new File(fileArchived + "\\" + child.getName()));
				}
			}
		} else {
			System.out.println(fileCurrent + "  Folder does not exists");
		}
	}

	public static void movingScreenshotsInArchive() {
		// TODO code application logic here
		// File (or directory) to be moved
		File fileCurrent = new File(System.getProperty("user.dir") + "\\Current Screenshots\\");
		// Destination directory
		File fileArchived = new File(System.getProperty("user.dir") + "\\Archived Screenshots\\");
		// Check weather source exists and it is folder.
		if (fileCurrent.exists() && fileCurrent.isDirectory()) {
			// Get list of the files and iterate over them
			File[] listOfFiles = fileCurrent.listFiles();
			if (listOfFiles != null) {
				for (File child : listOfFiles) { // Move files to destination folder
					child.renameTo(new File(fileArchived + "\\" + child.getName()));
				}
			}
		} else {
			System.out.println(fileCurrent + "  Folder does not exists");
		}
	}

	public void explicitWaitForElement(WebElement element) throws Error {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
	}
}