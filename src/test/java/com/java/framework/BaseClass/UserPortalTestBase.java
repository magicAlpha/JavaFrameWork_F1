package com.java.framework.BaseClass;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.java.framework.Utils.EnvironmentPropertiesReader;

public class UserPortalTestBase {

	protected static WebDriver driver;
	public Properties prop;
	public FileInputStream fis;

	@BeforeClass
	@Parameters({ "browser" })
	public void InitializeTestBaseSetup(String browser) {

		prop = EnvironmentPropertiesReader.getInstance().PropertiesFile();
		String chPath = prop.getProperty("ChromeDriverPath");
		String ffPath = prop.getProperty("FireFoxDriverPath");
		String iePath = prop.getProperty("IEDriverPath");
		String url = prop.getProperty("UserURL");
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chPath);
				driver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("ff")) {
				System.setProperty("webdriver.gecko.driver", ffPath);
				driver = new FirefoxDriver();
			}

			else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", iePath);
				driver = new InternetExplorerDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@AfterClass
	public void TearDownApplication() {

		if (driver != null) {
			driver.quit();
		}
	}

}
