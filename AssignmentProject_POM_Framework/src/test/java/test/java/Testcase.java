package test.java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Testcase {
	static WebDriver driver;
	String dateToVerify=null;
	String slotTime=null;
	@BeforeTest
	public void testSetup() {
		
	WebDriverManager.chromedriver().setup();
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://virtual.gleantap.org/");
	}
	
	@Test
	public void login() {
		
		driver.findElement(By.xpath("(//a[text()='Sign In'])[2]")).click();
		driver.findElement(By.xpath("(//input[@id='gl_username'])[1]")).sendKeys("shubham@gleantap.com");
		driver.findElement(By.xpath("(//input[@id='gl_password'])[1]")).sendKeys("Test1234");
		driver.findElement(By.xpath("(//input[@value='Sign in'])[1]")).click();
		
	}
	
	@Test
	public void verifyTitle() throws InterruptedException {
		Thread.sleep(3000);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "My Bookings | Gleantapbuzz");
	}
	
	@Test
	public void verifyBook1On1SuccessMessage() {
		
		driver.findElement(By.xpath("//span[text()=['Book a 1-on-1']")).click();
		driver.findElement(By.xpath("//a[text()='John Foster']")).click();
		
		List<WebElement> calendarDates = driver.findElements(By.xpath("(//a[@data-id])"));
		List<WebElement> morningSlots = driver.findElements(By.xpath("(//div[text()='Morning'])"));
		int slotAvailable = 0;

		// Loop for iterating the date row

		for (int dateIndex = 1; dateIndex <= calendarDates.size(); dateIndex++) {

			driver.findElement(By.xpath("(//a[@data-id])[" + dateIndex + "]")); //// div[contains(@data-date,'22')]

			String dateSelected = driver.findElement(By.xpath("(//a[@data-id])[" + dateIndex + "]/span[1]")).getText();
			dateToVerify=driver.findElement(By.xpath("(//a[@data-id])[" + dateIndex + "]")).getText();
			
			// Loop for iterating the slot column

			for (int slotIndex = 1; slotIndex <= morningSlots.size(); slotIndex++) {

				String status = driver
						.findElement(
								By.xpath("(//div[contains(@data-date,'" + dateSelected + "')])[" + slotIndex + "]"))
						.getAttribute("class");

				if (status.contains("book_trainer_slot")) {
					driver.findElement(By.xpath("(//div[contains(@data-date,'" + dateSelected + "')])[" + slotIndex + "]"));
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
		String successMessage = driver.findElement(By.xpath("//span[text()='Session successfully booked']")).getText();
		Assert.assertEquals(successMessage, "Session successfully booked");
	}
	
	@Test
	public void verifySlotOnMyBookings() {
		
		driver.findElement(By.xpath("//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='My Bookings']")).click();
		
		//Loop to verify slot, Instructor name and Confirmed Status from the available records
		
		List<WebElement> Instructor=driver.findElements(By.xpath("(//div[text()='John Foster'])"));
		
		for(int instructorIndex=1; instructorIndex<=Instructor.size();instructorIndex++) {  //(//div[text()='John Foster'])[1]//preceding::td[1]
			
			String getInstructorName=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]")).getText();
			String getStatus=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//following::td[1]")).getText();
			String getDateTime=driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//preceding::td[1]")).getText();
			
			if(getInstructorName.equals("John Foster") && getStatus.equals("Confirmed") && getDateTime.contains(dateToVerify) && getDateTime.contains(slotTime)){
				new Actions(driver).moveToElement(driver.findElement(By.xpath("(//div[text()='John Foster'])[" + instructorIndex + "]//preceding::td[1]"))).perform();
				
				System.out.println("Booked slot is available on My bookigs page");
				
				break;
			}
			else {
				continue;
			}
					
		}
		
		
	}
}

