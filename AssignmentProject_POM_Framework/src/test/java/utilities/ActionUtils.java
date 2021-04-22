package utilities;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class ActionUtils
{
	static ExtentTest logger;
	static String concatenate = ".";
	static ITestResult result;
	private static final int tIMER_WAIT = 200;


	public  void clearAndSendKeysByXpath(WebDriver driver, String pageObject, String value)
	{
		// reusablemethods.TestWebDriverMethod.smallSleep();
		try
		{
			TestWebDriverMethod.setExplicitWait(driver,  pageObject);
			driver.findElement(By.xpath(pageObject)).clear();
			driver.findElement(By.xpath(pageObject)).sendKeys(value);
		}
		catch (Exception e)
		{
			
			TestWebDriverMethod.setExplicitWait(driver,  pageObject);
			driver.findElement(By.xpath(pageObject)).sendKeys(value);
		}
	}
	
   

	public static void captureScreenshot(WebDriver driver, ExtentTest logger) throws IOException {


		String screenshotPath = concatenate + TestCaseStatus.getStepsScreenshot(driver, "InfoScreenshot");

		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotPath));
	}
	

	
	public  void clickByXpath(WebDriver driver, String pageObject)
	{
		// verifyVisibilityOfLocatedElementByXpath(driver, pageObject);
		TestWebDriverMethod.setExplicitWait(driver, pageObject);
		driver.findElement(By.xpath(pageObject)).click();
	}
	public  void waitForElementTobeVisible(WebDriver driver, String locator)
	{
		new WebDriverWait(driver, tIMER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public static void moveToElement(WebDriver driver, WebElement element)
	{
		

		new Actions(driver).moveToElement(element).perform();
	}
}


