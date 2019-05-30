package RoughWork;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.framework.Utils.EnvironmentPropertiesReader;

public class GetAllElementsOfDOM {
	
	static EnvironmentPropertiesReader en = EnvironmentPropertiesReader.getInstance();
	static WebDriver driver;
	static String url = en.getProperty("SpreadSheetURL");		
	static String browserPath = System.getProperty("user.dir") + en.getProperty("ChromeDriverPath");

	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", browserPath);
		driver = new ChromeDriver();				
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(url);
		
		String pageSource=driver.getPageSource();
		System.out.println(pageSource);
		
		List<WebElement>listOfWebElement=driver.findElements(By.xpath("//*"));
		
		List<WebElement>listOfByTagName=driver.findElements(By.tagName("input"));
		/*
		List<WebElement>listOfByTagNameA=driver.findElements(By.tagName("a"));
		List<WebElement>listOfByTagNameDiv=driver.findElements(By.tagName("div"));
		List<WebElement>listOfByTagNameUl=driver.findElements(By.tagName("ul"));
		List<WebElement>listOfByTagNameLi=driver.findElements(By.tagName("li"));
		List<WebElement>listOfByTagNameImg=driver.findElements(By.tagName("img"));
		List<WebElement>listOfByTagNamehl=driver.findElements(By.tagName("h1"));
		List<WebElement>listOfByTagNameSection=driver.findElements(By.tagName("section"));
		List<WebElement>listOfByTagNameClass=driver.findElements(By.tagName("class"));
		List<WebElement>listOfByTagNameP=driver.findElements(By.tagName("p"));
		*/
		/*
		int totalElementPresentInDOM=listOfWebElement.size();
		System.out.println(totalElementPresentInDOM);		
		for(WebElement element: listOfWebElement)
		{
			System.out.println(element.getTagName() + " : " + element.getText());
		}
		*/
		for(WebElement element: listOfByTagName)
		{
			System.out.println(element+" : "+element.findElement(By.xpath("//*")));
		}
	}
	

}
