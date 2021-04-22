package Book1On1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ActionUtils;
import utilities.DataProviderClass;

public class Actions extends ActionUtils {

	String dateToVerify= null;
	String slotTime = null;

	DataProviderClass data = new DataProviderClass();


	public void clickSignInbuttonHomePage(WebDriver driver) {

		clickByXpath(driver, PageObjects.signInButton);
	}

	public void enterUsername(WebDriver driver) {

		clearAndSendKeysByXpath(driver, PageObjects.userNameTextBox, data.username);
	}

	public void enterPassword(WebDriver driver) {

		clearAndSendKeysByXpath(driver, PageObjects.passwordTextBox, data.password);
	}

	public void clickSignInButton(WebDriver driver) {

		clickByXpath(driver, PageObjects.signInButtonOnLoginPage);
	}

	public void verifyTitle(WebDriver driver) {

		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "My Bookings | Gleantapbuzz");
	}

	public void waitForElement(WebDriver driver, String locator) {

		waitForElementTobeVisible(driver, locator);
	}

	public void clickBook1On1(WebDriver driver) {

		clickByXpath(driver, PageObjects.book1On1Link);
	}

	public void clickOnJohnFoster(WebDriver driver) {

		clickByXpath(driver, PageObjects.johnForster);
	}

	public void clickOnDate(WebDriver driver) {

		clickByXpath(driver, PageObjects.date);
	}

	public void clickOnTime(WebDriver driver) {

		clickByXpath(driver, PageObjects.time);
	}
	public void clickOnMyAccount(WebDriver driver) {

		clickByXpath(driver, PageObjects.myAccountbutton);
	}
	
	public void clickOnMyBookings(WebDriver driver) {

		clickByXpath(driver, PageObjects.myBookingsLink);
	}
	
	
	public void selectAvailableSlot(WebDriver driver) {

		List<WebElement> calendarDates = driver.findElements(By.xpath("(//a[@data-id])"));
		List<WebElement> morningSlots = driver.findElements(By.xpath("(//div[text()='Morning'])"));
		int slotAvailable = 0;

		////////// Loop for iterating the date row////////////

		for (int dateIndex = 1; dateIndex <= calendarDates.size(); dateIndex++) {

			clickByXpath(driver, "(//a[@data-id])[" + dateIndex + "]"); //// div[contains(@data-date,'22')]

			String dateSelected = driver.findElement(By.xpath("(//a[@data-id])[" + dateIndex + "]/span[1]")).getText();
			dateToVerify=driver.findElement(By.xpath("(//a[@data-id])[" + dateIndex + "]")).getText();
			////////////// Loop for iterating the Morning slot column///////////////

			for (int slotIndex = 1; slotIndex <= morningSlots.size(); slotIndex++) {

				String status = driver
						.findElement(
								By.xpath("(//div[contains(@data-date,'" + dateSelected + "')])[" + slotIndex + "]"))
						.getAttribute("class");

				if (status.contains("book_trainer_slot")) {
					clickByXpath(driver, "(//div[contains(@data-date,'" + dateSelected + "')])[" + slotIndex + "]");
					slotTime = driver
							.findElement(
									By.xpath("(//div[contains(@data-date,'" + dateSelected + "')])[" + slotIndex + "]"))
							.getText();
					slotAvailable++;
					System.out.println("slot is available");
					break;
				}

				else {
					System.out.println("Slot is already booked");
				}
			}

			if (slotAvailable == 1) {
				break;
			}
		}

		System.out.println("date: " + dateToVerify + " Time slot: " + slotTime);

	}

	public String getSuccessMessage(WebDriver driver) {

		String successMessage = driver.findElement(By.xpath("//span[text()='Session successfully booked']")).getText();
		return successMessage;
	}
	
	public void verifySlotOnMyBookings(WebDriver driver, ExtentTest logger) {
		List<WebElement> Instructor=driver.findElements(By.xpath("(//div[text()='John Foster'])"));
		
		for(int instructorIndex=1; instructorIndex<=Instructor.size();instructorIndex++) {  //(//div[text()='John Foster'])[1]//preceding::td[1]
			
			String getInstructorName=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]")).getText();
			String getStatus=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//following::td[1]")).getText();
			String getDateTime=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//preceding::td[1]")).getText();
			
			if(getInstructorName.equals("John Foster") && getStatus.equals("Confirmed") && getDateTime.contains(dateToVerify) && getDateTime.contains(slotTime)){
				moveToElement(driver, driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//preceding::td[1]")));
				logger.log(LogStatus.PASS, "Date, Timeslot and Instructor name has been confirmed");	
				break;
			}
			else {
				continue;
			}
					
		}
		
	}
}
