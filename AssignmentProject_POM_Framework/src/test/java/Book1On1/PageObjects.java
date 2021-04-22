package Book1On1;

import org.openqa.selenium.WebDriver;

import utilities.DataProviderClass;

public class PageObjects {

	static DataProviderClass dat2 = new DataProviderClass();
	public static String signInButton="(//a[text()='Sign In'])[2]";
	public static String signInText="//div[text()='Sign in']";
	public static String userNameTextBox="(//input[@id='gl_username'])[1]";
	public static String passwordTextBox="(//input[@id='gl_password'])[1]";
	public static String signInButtonOnLoginPage="(//input[@value='Sign in'])[1]";
	public static String book1On1Link="//span[text()='Book a 1-on-1']";
	public static String johnForster="//a[text()='John Foster']";
	public static String date = "//option[@value='" + dat2.date + "']";
	static String date1="tab"+dat2.date;
	public static String time="//div[@id='" + date1 + "']/div[1]/div[1]/div[1]/div[1]//following::div[1]/label[1]";
	public static String dateRow="(//a[@data-id])[1]";
	public static String morningColumn="(//div[text()='Morning'])[1]//following::div[contains(@class,'cancel-class')][1]";
	public static String myAccountbutton="//a[text()='My Account']";
	public static String myBookingsLink="//span[text()='My Bookings']";

}
