package RoughWork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyColors {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void initWebBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\dheeraj.singh\\eclipse-workspace\\JavaFrameWork\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.walmart.com/account/login?tid=0&returnUrl=%2F");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void VerifyColor() {
		String cssValueOfColor;
		String cssValueOfBackgroundColor;
		String expectedColor="#0065ff";
		wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button m-margin-top' and @data-automation-id='signin-submit-btn' and text()='Sign In']")));
		
		cssValueOfColor = driver.findElement(By.xpath("//button[@class='button m-margin-top' and @data-automation-id='signin-submit-btn' and text()='Sign In']")).getCssValue("color");
		System.out.println(cssValueOfColor);
		
		cssValueOfBackgroundColor = driver.findElement(By.xpath("//button[@class='button m-margin-top' and @data-automation-id='signin-submit-btn' and text()='Sign In']")).getCssValue("background-color");
		System.out.println(cssValueOfBackgroundColor);
		
		String actualColor=Color.fromString(cssValueOfBackgroundColor).asHex();
		System.out.println(actualColor);
		Assert.assertEquals(actualColor, expectedColor);
		
		
		String[] finalRGBAvalue=cssValueOfColor.replace("rgba(", "").replace(")", "").split(",");
		String[] finalRGBAvalue1=cssValueOfBackgroundColor.replace("rgba(", "").replace(")", "").split(",");
		
		int totalLength=finalRGBAvalue.length;
		for(int i=0;i<totalLength;i++)
		{
			System.out.println(finalRGBAvalue[i]);
		}
		
		
		System.out.println("====================Color=====================");
	
		finalRGBAvalue[0]=finalRGBAvalue[0].trim();		
		int value1=Integer.parseInt(finalRGBAvalue[0]);		
		System.out.println(value1);
		
		finalRGBAvalue[1]=finalRGBAvalue[1].trim();
		int value2=Integer.parseInt(finalRGBAvalue[1]);
		System.out.println(value2);
		
		
		finalRGBAvalue[2]=finalRGBAvalue[2].trim();
		int value3=Integer.parseInt(finalRGBAvalue[2]);
		System.out.println(value3);
		
		finalRGBAvalue[3]=finalRGBAvalue[3].trim();
		int value4=Integer.parseInt(finalRGBAvalue[3]);
		System.out.println(value4);
		
		String actualColor2 = String.format("#%02x%02x%02x", value1, value2, value3);
		System.out.println(actualColor2);

		
		System.out.println("====================Background-Color=====================");
		
		finalRGBAvalue1[0]=finalRGBAvalue1[0].trim();		
		int value11=Integer.parseInt(finalRGBAvalue1[0]);		
		System.out.println(value11);
		
		finalRGBAvalue1[1]=finalRGBAvalue1[1].trim();
		int value21=Integer.parseInt(finalRGBAvalue1[1]);
		System.out.println(value21);
		
		
		finalRGBAvalue1[2]=finalRGBAvalue1[2].trim();
		int value31=Integer.parseInt(finalRGBAvalue1[2]);
		System.out.println(value31);
		
		finalRGBAvalue1[3]=finalRGBAvalue1[3].trim();
		int value41=Integer.parseInt(finalRGBAvalue1[3]);
		System.out.println(value41);
		
		String actualBackGroundColor = String.format("#%02x%02x%02x", value1, value2, value3);
		System.out.println(actualBackGroundColor );


	}

}
