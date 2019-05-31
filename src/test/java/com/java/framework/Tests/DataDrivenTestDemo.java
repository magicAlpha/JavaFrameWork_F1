package com.java.framework.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java.framework.Utils.EnvironmentPropertiesReader;

public class DataDrivenTestDemo {
	
	
	EnvironmentPropertiesReader obj=EnvironmentPropertiesReader.getInstance();
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
	
	@Test()
	public void LoginTest()
	{
		File file;
		FileInputStream fis;
		Workbook wb;
		Sheet sheet;
		Row row;
		Cell cell;
		
		String excelSheet=System.getProperty("user.dir")+obj.getProperty("ExcelFile");

		try
		{
			
			WebElement userName=driver.findElement(By.xpath("//input[@id='username']"));
			WebElement passWord=driver.findElement(By.xpath("//input[@id='password']"));
			WebElement loginBtn=driver.findElement(By.xpath("//button[@id='loginBtn']"));			
			String userTextBoxValue="";
			String passTextBoxValue="";
			file=new File(excelSheet);
			fis=new FileInputStream(file);
			String user="";
			String pass="";
			if(excelSheet.endsWith(".xlsx"))
			{
				wb=new XSSFWorkbook(fis);
			}
			else
			{
				wb=new HSSFWorkbook(fis);
			}
			
			sheet=wb.getSheet("LoginData");
			int totalRow=sheet.getLastRowNum();
			
			for(int i=1;i<totalRow;i++)
			{
				row=sheet.getRow(i);
				int totalCell=row.getLastCellNum();
				
				for(int j=0;j<totalCell-1;j++)
				{
					cell=row.getCell(j);
					int k=0;
					cell.setCellType(CellType.STRING);
					user=sheet.getRow(i).getCell(k).getStringCellValue().toString().trim();
					pass=sheet.getRow(i).getCell(k+1).getStringCellValue().toString().trim();
					userTextBoxValue=userName.getAttribute("value");											
					if(!userTextBoxValue.isEmpty())
					{
						Thread.sleep(2000);
						userName.clear();
						Thread.sleep(2000);
					}					
					userName.sendKeys(user);				
					passTextBoxValue=passWord.getAttribute("value");
					if(!passTextBoxValue.isEmpty())
					{
						Thread.sleep(2000);
						passWord.clear();
						Thread.sleep(2000);
					}
					passWord.sendKeys(pass);
					loginBtn.click();
				}
				
			}
			
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
			if(driver==null)
			{
				driver.quit();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
