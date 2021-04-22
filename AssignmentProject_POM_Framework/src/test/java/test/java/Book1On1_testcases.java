package test.java;

import Book1On1.PageObjects;
import Book1On1.Steps;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import org.testng.annotations.Test;

import utilities.ActionUtils;
import utilities.BrowserSelection;
import utilities.Login;
import utilities.TestWebDriverMethod;
import utilities.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Book1On1_testcases extends Steps {

	private static String timeStamp = null;
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest logger;
	static Properties prop = new Properties();
	static Properties rootprop = new Properties();
	static String reportLocation = prop.getProperty("ExtentReports");
	static String imageLocation = "images/";
	TestCaseStatus results = new TestCaseStatus();
	static ATUTestRecorder recorder;
	
///////////////////////////////////////Before Test Setup//////////////////////////////////////////////////////////	
	@BeforeTest
	public void setup() throws Exception {
		utilities.BeforeTest.beforeTestSetup(prop);
		timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime()).toString();
		report = new ExtentReports(prop.getProperty("ExtentReports") + "Test_Results_" + timeStamp + ".html");
		String browserName = BrowserSelection.getParameter("browser");
		driver = BrowserSelection.browser(driver, browserName);
		utilities.BeforeTest.postBrowserLaunch(driver, prop);
		recorder = new ATUTestRecorder("D:\\ScriptVideos\\","TestVideo-",false);
		recorder.start();  
	}
////////////////////////////////////////Test Cases///////////////////////////////////////////////////
	@Test()
	public void login() throws Exception {
		try {
			logger = report.startTest("To validate login functionality");
			loginSteps(driver, logger);
		} catch (Exception e) {
			captureScreenshot(driver, logger);
			logger.log(LogStatus.FAIL, "Login has been ailed");

		}
	}
	@Test(dependsOnMethods= {"login"})
	public void verifyMyBookingPageTitle() throws Exception {
		try {
			logger = report.startTest("To validate My Bookings page title");
			myBookingTitleSteps(driver, logger);
		} catch (Exception e) {
			captureScreenshot(driver, logger);
			logger.log(LogStatus.FAIL, "Title not verified");

		}
	}
	
	@Test(dependsOnMethods= {"login"})
	public void verifyBook1On1SuccessMessage() throws Exception {
		try {
			logger = report.startTest("To validate Book1on1 success message");
			book1On1Steps(driver, logger);
			
		} catch (Exception e) {
			captureScreenshot(driver, logger);
			logger.log(LogStatus.FAIL, "Success message not verified");
			Assert.fail("Success message not verified");
		}
	}
	
	@Test(dependsOnMethods= {"login"})
	public void verifySlotOnMyBookings() throws Exception {
		try {
			logger = report.startTest("To validate booked Date and time slot on My bookings page");
			verifySlotBookedOnMyBookings(driver, logger);
			
		} catch (Exception e) {
			captureScreenshot(driver, logger);
			logger.log(LogStatus.FAIL, "Success message not verified");
			Assert.fail("Success message not verified");
		}
	}
////////////////////////////////////After Method and Test////////////////////////////////////////////////
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		results.getResult(result, driver, logger);
	}

	@AfterTest
	public static void quit() throws ATUTestRecorderException {
		report.flush();
		recorder.stop();
	}
}
