package RoughWork;

import java.util.Date;
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
	int enterNumberOfMonth=3;

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
			System.out.println(e.getStackTrace());
			System.out.println(e.getCause());
		}
	}

	@Test
	public void ClickOnDate() {
		try {

			// elements of UI
			WebElement txtDepartingDate = driver.findElement(By.xpath("//input[@id='txtDepartingDate']"));
			WebElement txtReturnDate = driver.findElement(By.xpath("//input[@id='txtReturnDate']"));
			//WebElement nextDate = driver.findElement(By.xpath("//a[@title='Next']"));

			// Wait for the element to be click for 20 seconds and will click on Dapart date
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(txtDepartingDate));
			txtDepartingDate.click();
			
			for(int i=0;i<enterNumberOfMonth;i++)
			{				
				action =new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//a[@title='Next']"))).build().perform();
				driver.findElement(By.xpath("//a[@title='Next']")).click();
			}
			
			String selectDayToBeClick="18";
			int n2=Integer.parseInt(selectDayToBeClick);
			List<WebElement> dayNumber=driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfDay=dayNumber.size();
			
			for(int i=0;i<totalNumberOfDay;i++)
			{
				WebElement element=dayNumber.get(i);
				String day=element.getText();
				int n1=Integer.parseInt(day);
				
				if(n2==n1);
				{
					element.click();					
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getCause());
		}
	}

}
