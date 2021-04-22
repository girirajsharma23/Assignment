package Book1On1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ActionUtils;
import utilities.DataProviderClass;

public class Steps extends Actions {

	ActionUtils actionUtil = new ActionUtils();
	DataProviderClass data = new DataProviderClass();
	SoftAssert verify = new SoftAssert();

	public void loginSteps(WebDriver driver, ExtentTest logger) {

		clickSignInbuttonHomePage(driver);
		waitForElement(driver, PageObjects.signInText);
		enterUsername(driver);
		enterPassword(driver);
		clickSignInButton(driver);

	}

	public void myBookingTitleSteps(WebDriver driver, ExtentTest logger) {

		verifyTitle(driver);

	}

	public void book1On1Steps(WebDriver driver, ExtentTest logger) {

		clickBook1On1(driver);
		clickOnJohnFoster(driver);
		selectAvailableSlot(driver);
		Assert.assertEquals(getSuccessMessage(driver), "Session successfully booked");
		logger.log(LogStatus.PASS, "Success message is verified- Message is: "+getSuccessMessage(driver));
	}
	
	public void verifySlotBookedOnMyBookings(WebDriver driver, ExtentTest logger) {

		clickOnMyAccount(driver);
		clickOnMyBookings(driver);
		verifySlotOnMyBookings(driver,logger);
		
	}
}
