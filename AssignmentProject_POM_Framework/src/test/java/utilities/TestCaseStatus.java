package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseStatus {
	static String reportLocation;
	static String imageLocation = "images/";

	

	public static String getFailScreenshot(WebDriver driver, String screenshotName) {

		String dateName = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime()).toString();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destination = "./" + "ExtentReports/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(scrFile, finalDestination);
		} catch (IOException e) {
		}
		return destination;
	}
	
	public static String getStepsScreenshot(WebDriver driver, String screenshotName) {

		 String dateName = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime()).toString();

	
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destination = "./" + "ExtentReports/StepsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(scrFile, finalDestination);
		} catch (IOException e) {
		}
		return destination;
	}

	public static String getPassScreenshot(WebDriver driver, String screenshotName) {

		String dateName = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime()).toString();

	
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destination = "./" + "ExtentReports/PassTestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(scrFile, finalDestination);
		} catch (IOException e) {
		}
		return destination;
	}
		public static String getSkipScreenshot(WebDriver driver, String screenshotName) {

			String dateName = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime()).toString();

		
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String destination = "./" + "ExtentReports/SkipTestsScreenshots/" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			try {
				FileUtils.copyFile(scrFile, finalDestination);
			} catch (IOException e) {
			}
			return destination;
		
	
		
	}
	
		public void getResult(ITestResult result, WebDriver driver, ExtentTest logger) throws IOException {

			String concatenate = ".";
			if (result.getStatus() == ITestResult.FAILURE) {
				logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
				logger.log(LogStatus.FAIL, "Test Case Failed due to " + result.getThrowable());
				// To capture screenshot path and store the path of the screenshot in the string
				// "screenshotPath"
				// We do pass the path captured by this mehtod in to the extent reports using
				// "logger.addScreenCapture" method.
				String screenshotPath = concatenate + TestCaseStatus.getFailScreenshot(driver, result.getName());
				// To add it in the extent report
				logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			}

			else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
			}

			else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
				String screenshotPath = concatenate + TestCaseStatus.getPassScreenshot(driver, result.getName());
				logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));

				// logger.log(LogStatus.FAIL, "Test Case Passed is "+result.getThrowable());
			}

		}
	
	
}
