package com.java.framework.TestClasses;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java.framework.Utils.EnvironmentPropertiesReader;

public class VerifyDownLoadFile {
	
	
	EnvironmentPropertiesReader en;
	WebDriver driver;
	WebDriverWait wait;
	Logger log=Logger.getLogger("devpinoyLogger");
	
	@BeforeTest
	public void InitWebBrowser()
	{
		en=EnvironmentPropertiesReader.getInstance();
		String url=en.getProperty("SpreadSheetURL");
		String browserPath=System.getProperty("user.dir")+en.getProperty("ChromeDriverPath");
		
		try
		{
			log.info("Setting browser properties");
			System.setProperty("webdriver.chrome.driver", browserPath);
			log.info("Instatiating browser driver");
			driver=new ChromeDriver();
			log.info("Hitting URL : --> " + url);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void VerifyDownLoadedFile()
	{
		WebElement animatedColorsBtn;
		File file;
		String downloadedFilePath="C:\\Users\\dheeraj.singh\\Downloads";
		try
		{
			log.info("Clicked on animatedColors button");
			animatedColorsBtn=driver.findElement(By.xpath("//a[text()='animatedcolors.xlsm']"));			
			wait=new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.elementToBeClickable(animatedColorsBtn)).click();
			log.info("file got downloaded");
			file=new File(downloadedFilePath);
			String fileName="";
			File[] files=file.listFiles();
			
			String downloadedFile=files[0].getName().toString().trim();
			System.out.println("Downloaded file is --> "+ downloadedFile);			
			int totalFilePresentAtDownload=files.length;
			for(int i=0;i<totalFilePresentAtDownload;i++)
			{
				fileName=files[i].getName().toString().trim();				
				System.out.println(fileName);				
				if(downloadedFile.equalsIgnoreCase(fileName))
				{
					log.info("Downloaded file name is + "+fileName +" and downloaded dateTime is : ");
					System.out.println("Download file has been verified -> "+fileName);
				}
				else
				{
					System.out.println("Download file has not been verified -> ");
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}

}
