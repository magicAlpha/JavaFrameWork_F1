package com.java.framework.TestClasses;

import java.util.concurrent.TimeUnit;

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

public class ExecutingTestMultipleTimes {
	
	
	EnvironmentPropertiesReader obj;
	String chromePath=null;
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void InitBrowser()
	{		
		try
		{			
			obj=EnvironmentPropertiesReader.getInstance();
			chromePath=System.getProperty("user.dir")+obj.getProperty("ChromeDriverPath");			
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			driver.get(obj.getProperty("Url"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(invocationCount=4)
	public void LoginTest()
	{
		try
		{
			WebElement userName=driver.findElement(By.xpath("//input[@id='username']"));
			WebElement passWord=driver.findElement(By.xpath("//input[@id='password']"));
			WebElement loginBtn=driver.findElement(By.xpath("//button[@id='loginBtn']"));			
			
			userName.sendKeys(obj.getProperty("Username"));
			passWord.sendKeys(obj.getProperty("Password"));
			loginBtn.click();
			WebElement accountMenuBtn=driver.findElement(By.xpath("//a[@id='account-menu']"));
			WebElement signOutBtn=driver.findElement(By.xpath("//a[@id='signout']"));
			
			wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(accountMenuBtn));
			accountMenuBtn.click();
			
			wait=new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(signOutBtn));
			signOutBtn.click();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void QuiteBrowser()
	{
		try
		{
			if(driver !=null)
			{
				driver.quit();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
