package utilities;
import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;

public class BrowserSelection
{
	
	
	public static WebDriver initializeBrowser(int type) {
		WebDriver driver = null;
 
		switch (type) {
			case 1:
			/*
			 * ChromeOptions chromeOptions = new ChromeOptions();
			 * chromeOptions.addArguments("--start-maximized");
			 */
				driver = new ChromeDriver();
                break;
 
			case 2:
				driver = new FirefoxDriver();
                break;
 
			case 3:
				driver = new EdgeDriver();
                break;
		}
 
		return driver;
	}
	
	static WebDriver driver;
	static Properties prop = new Properties();
	public static WebDriver browser(WebDriver driver, String browserName)
	{
		
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			//WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", prop.getProperty("Chrome"));
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", prop.getProperty("Firefox"));
			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();	
			driver = new EdgeDriver();
		}
		if (browserName.equalsIgnoreCase("Headless"))
		{
			driver = new HtmlUnitDriver(true);
		}
		return driver;
	}

	public static String getParameter(String name)
	{
		utilities.BeforeTest.beforeTestSetup(prop);
		String value = prop.getProperty(name);
		if (value == null)
			throw new RuntimeException(name + " is not a parameter!");
		if (value.isEmpty())
			throw new RuntimeException(name + " is empty!");
		return value;
	}
}
