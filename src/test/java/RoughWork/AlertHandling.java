package RoughWork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertHandling {
	
	
	public static void main(String[] args) {
	
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Users\\dheeraj.singh\\eclipse-workspace\\JavaFrameWork\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demo.guru99.com/test/delete_customer.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.findElement(By.name("cusid")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.switchTo().alert();
		
		String alertName=driver.switchTo().alert().getText();
		System.out.println(alertName);
		
		
	}

}
