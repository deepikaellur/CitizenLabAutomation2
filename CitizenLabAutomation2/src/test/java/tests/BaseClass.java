package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void reportSetup() {

		// start reporters
		htmlReporter =  new ExtentSparkReporter ("extent.html");
		// create EtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	
	@BeforeTest
	public void setUp() {
		
			System.setProperty("webdriver.chrome.driver","//Users//deepika//Documents//Eclipse_Projects//chromedriver");
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	}


	@AfterSuite
	public void tearDown() {

		driver.quit();
		extent.flush();

	}

}
