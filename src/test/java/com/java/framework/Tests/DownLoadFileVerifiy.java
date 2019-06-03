package com.java.framework.Tests;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.java.framework.Utils.EnvironmentPropertiesReader;

public class DownLoadFileVerifiy {

	ExtentHtmlReporter e1;
	ExtentReports e2;
	ExtentTest e3;
	File file;
	EnvironmentPropertiesReader en = EnvironmentPropertiesReader.getInstance();
	WebDriver driver;
	WebDriverWait wait;
	Logger log = Logger.getLogger("devpinoyLogger");;

	String downloadedFilePath = "C:\\Users\\dheeraj.singh\\Downloads";
	String url = en.getProperty("SpreadSheetURL");
	String browserPath = System.getProperty("user.dir") + en.getProperty("ChromeDriverPath");
	String downloadedFileName = en.getProperty("DownloadedFileName");
	String extentReportPath = System.getProperty("user.dir") + "/ExtentReport/" + this.getClass().getSimpleName()+".html";

	@BeforeSuite
	public void InitializeExtentReport() {
		try {
			PropertyConfigurator.configure(
					"C:\\Users\\dheeraj.singh\\eclipse-workspace\\JavaFrameWork\\src\\main\\java\\com\\java\\framework\\Config\\log4j.properties");
	
			file = new File(extentReportPath);
			e1 = new ExtentHtmlReporter(file);
			e2 = new ExtentReports();
			e2.attachReporter(e1);

			e2.setSystemInfo("Tester Name", "Dheeraj Pratap Singh");
			e2.setSystemInfo("UserName", "dheeraj.singh@google.com");
			e2.setSystemInfo("Environment", "Production");
			e2.setSystemInfo("Sprint Name", "Flash");
			e2.setSystemInfo("Version Number", "ASHSJ1324_234");
			e2.setSystemInfo("Host", "127.0.0.1");

			e1.config().setDocumentTitle("This is automation testing module");
			e1.config().setReportName("Extent Report generator testing demo.");
			e1.config().setTheme(Theme.DARK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void CreateReport(Method method) {
		try {
			e3 = e2.createTest(this.getClass().getSimpleName() + " : : " + method.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void GetReportResult(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test started");
				log.info(result.getMethod().getMethodName() + " test started");
				e3.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
				log.info(result.getMethod().getMethodName() + " test completed");
				e3.log(Status.PASS, result.getMethod().getMethodName() + " test passed");
				log.info(result.getMethod().getMethodName() + " test passed");
			}

			if (result.getStatus() == ITestResult.FAILURE) {
				log.info(result.getMethod().getMethodName() + " test started");
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test started");
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
				e3.log(Status.FAIL, result.getMethod().getMethodName() + " test failed");
				log.info("test failed due to below reason");
				e3.log(Status.FAIL, result.getThrowable().getCause());
				e3.log(Status.FAIL, result.getThrowable().getMessage());				
			}

			if (result.getStatus() == ITestResult.SKIP) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test started");
				e3.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.YELLOW));
				e3.log(Status.SKIP, result.getMethod().getMethodName() + " test skipped");
				e3.log(Status.SKIP, result.getThrowable());
				log.info("test has been skipped");
			}

			if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
				e3.log(Status.FAIL, result.getThrowable());
				e3.log(Status.FAIL, result.getMethod().getMethodName()
						+ " module passed but with on Test Failed But Within Success Percentage.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void FlushExtentReport() {
		try {
			e2.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String format(long timeValue) {

		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date time = new Date(timeValue);
		String dateTimeConvertToString = dateFormatter.format(time);
		return dateTimeConvertToString;
	}

	@Test
	public void VerifyDownLoadFile() {
		WebElement animatedColorsBtn;
		try {
			log.info("Setting browser properties");
			System.setProperty("webdriver.chrome.driver", browserPath);
			log.info("Instatiating browser driver");
			driver = new ChromeDriver();
			log.info("Hitting URL : --> " + url);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
			log.info("Clicked on animatedColors button");
			animatedColorsBtn = driver.findElement(By.xpath("//a[text()='animatedcolors.xlsm']"));
			wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.elementToBeClickable(animatedColorsBtn)).click();

			// This method will check if downloaded file is verified or not
			boolean isDownloadedFileVerified = GetLatestDownloadedFile(downloadedFilePath, downloadedFileName);

			if (isDownloadedFileVerified) {
				log.info("Downloaded file has been verified");
				System.out.println("Downloaded file has been verified");
			} else {
				log.error("Not verified");
				System.out.println("Not verified");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	// This method will verify the downloaded file
	public boolean GetLatestDownloadedFile(String downloadedFilePath, String downloadedFileName) {
		boolean flag = false;
		File file;
		try {
			log.info("file name : " + downloadedFileName + "got downloaded");
			file = new File(downloadedFilePath);
			String downloadedFile = "";
			String storeDownloadedFile = "";
			String storeDownloadedFileDateTIme = "";
			File[] files = file.listFiles();
			int totalFilePresentAtDownload = files.length;
			for (int i = 0; i < totalFilePresentAtDownload; i++) {
				downloadedFile = files[i].getName().toString().trim();
				String downloadedFileDateTime = format(files[i].lastModified());
				System.out.println(downloadedFile + " | " + downloadedFileDateTime);
				if (downloadedFile.equalsIgnoreCase(downloadedFileName)) {
					flag = true;
					storeDownloadedFile = downloadedFile;
					storeDownloadedFileDateTIme = downloadedFileDateTime;
				}
			}
			if (flag) {
				log.info("Download file has been verified -> " + storeDownloadedFile + " with downloaded date and time "
						+ storeDownloadedFileDateTIme);
				System.out.println("Download file has been verified -> " + storeDownloadedFile
						+ " with downloaded date and time " + storeDownloadedFileDateTIme);
			} else {
				log.error("Download file has not been verified" + storeDownloadedFile);
				System.out.println("Download file has not been verified" + storeDownloadedFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return flag;
	}
}
