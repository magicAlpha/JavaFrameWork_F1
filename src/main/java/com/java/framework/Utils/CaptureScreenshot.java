package com.java.framework.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {

	String screenshotpath = System.getProperty("user.dir") + "/FailedTestScreenshots/" + this.getClass().getSimpleName()
			+ ".png";

	public String screenshot(WebDriver driver, String path) throws Exception {
		try {
			TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
			File file1 = takeScreenshot.getScreenshotAs(OutputType.FILE);
			File file2 = new File(screenshotpath);
			FileUtils.copyFile(file1, file2);
			
		} catch (Exception e) {
			throw e;
		}
		return screenshotpath;
	}
	
	public void TakeScreenshot(WebDriver driver, String fileName)
	{
		try
		{
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd-hhmmss-SSS" );
	        String filePath = "screenshots/"+fileName+" "+ sdf.format( cal.getTime() ) + ".jpg";
	        screenshot(driver, filePath);
		}
		catch (Exception e) {
			
		}
	}
}
