package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebDriverMethod {
	public static WebDriver getUrl(WebDriver driver, String url)
	{
		driver.get(url);
		return driver;
	}
	public static WebDriver maximizeBrowser(WebDriver driver)
	{
		
		driver.manage().window().maximize();
		
		return driver;
	}

	public static void setExplicitWait(WebDriver driver, String locator) {
		long timeout=10;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
}
