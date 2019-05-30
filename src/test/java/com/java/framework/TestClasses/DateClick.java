package com.java.framework.TestClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DateClick {

	String url = "https://www.tripiflights.com/";
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	int enterNumberOfMonth = 3;

	@BeforeTest
	public void OpenBrowser() {
		try {
			String driverPath = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void ClickOnDate() {
		try {

			// Web elements of UI
			WebElement txtDepartingDate = driver.findElement(By.xpath("//input[@id='txtDepartingDate']"));
			WebElement txtReturnDate = driver.findElement(By.xpath("//input[@id='txtReturnDate']"));
			// WebElement nextDate = driver.findElement(By.xpath("//a[@title='Next']"));

			// Wait for the element to be click for 20 seconds and will click on Dapart date
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(txtDepartingDate));
			txtDepartingDate.click();

			// Handle the staleElement reference exception using below loop
			for (int i = 0; i < enterNumberOfMonth; i++) {
				action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//a[@title='Next']"))).build().perform();
				driver.findElement(By.xpath("//a[@title='Next']")).click();
			}

			// Select departure date
			String selectDepartDate = "18";
			List<WebElement> departDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfDepartureDay = departDay.size();
			for (int i = 0; i < totalNumberOfDepartureDay; i++) {
				WebElement element = departDay.get(i);
				String departureDate = element.getText();

				if (selectDepartDate.equals(departureDate)) {
					element.click();
					break;
				}
			}

			// Handling satelElement reference using below loop
			for (int i = 0; i < enterNumberOfMonth; i++) {
				driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']"));
			}

			// Select return day
			String selectReturnDate = "7";
			List<WebElement> returnDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfReturnDay = returnDay.size();

			for (int i = 0; i < totalNumberOfReturnDay; i++) {
				WebElement element = returnDay.get(i);
				String returnDate = element.getText();

				if (selectReturnDate.equals(returnDate)) {
					element.click();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());		
		}
	}

}
