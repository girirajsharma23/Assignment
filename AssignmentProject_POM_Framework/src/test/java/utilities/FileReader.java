package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FileReader
{
	
	public static FileInputStream readFile(String FilePath, Properties prop)
	{
		File file = new File(FilePath);
		FileInputStream fileInput = null;
		try
		{
			fileInput = new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
		}
		try
		{
			prop.load(fileInput);
		}
		catch (IOException e)
		{
		}
		return fileInput;
	}
	
	
}
