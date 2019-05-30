package RoughWork;

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

import com.java.framework.Utils.EnvironmentPropertiesReader;

public class DownloadFileVerify {
	
	static EnvironmentPropertiesReader en=EnvironmentPropertiesReader.getInstance();;
	static WebDriver driver;
	static WebDriverWait wait;	
	static Logger log;
	public static void main(String[] args) {
		
		log=Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure("C:\\Users\\dheeraj.singh\\eclipse-workspace\\JavaFrameWork\\src\\main\\java\\com\\java\\framework\\Config\\log4j.properties");
		String downloadedFilePath="C:\\Users\\dheeraj.singh\\Downloads";
		String url=en.getProperty("SpreadSheetURL");
		String browserPath=System.getProperty("user.dir")+en.getProperty("ChromeDriverPath");
		String downloadedFileName=en.getProperty("DownloadedFileName");
		
		VerifyDownloadFile(browserPath, url, downloadedFilePath, downloadedFileName);
	}
	
	public static void VerifyDownloadFile(String browserPath, String url, String downloadedFilePath, String downloadedFileName)
	{
		WebElement animatedColorsBtn;				
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
			
			log.info("Clicked on animatedColors button");
			animatedColorsBtn=driver.findElement(By.xpath("//a[text()='animatedcolors.xlsm']"));			
			wait=new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.elementToBeClickable(animatedColorsBtn)).click();
			
			// This method will check if downloaded file is verified or not
			boolean isDownloadedFileVerified=GetLatestDownloadedFile(downloadedFilePath, downloadedFileName);
			
			if(isDownloadedFileVerified)
			{
				log.info("Downloaded file has been verified");
				System.out.println("Downloaded file has been verified");
			}
			else
			{
				log.error("Not verified");
				System.out.println("Not verified");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());			
		}
	}
	
	public static String format(long timeValue) {
		
	    DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    Date time=new Date(timeValue);
	    String dateTimeConvertToString=dateFormatter.format(time);
	    return dateTimeConvertToString;
	  }
	
	// This method will verify the downloaded file
	public static boolean GetLatestDownloadedFile(String downloadedFilePath, String downloadedFileName)
	{
		boolean flag=false;
		File file;
		try
		{
			log.info("file name : "+ downloadedFileName +"got downloaded");
			file=new File(downloadedFilePath);
			String downloadedFile="";
			String storeDownloadedFile="";
			String storeDownloadedFileDateTIme="";
			File[] files=file.listFiles();
			int totalFilePresentAtDownload=files.length;
			for(int i=0;i<totalFilePresentAtDownload;i++)
			{
				downloadedFile=files[i].getName().toString().trim();
				String downloadedFileDateTime=format(files[i].lastModified());
				System.out.println(downloadedFile + " | " + downloadedFileDateTime);
				if(downloadedFile.equalsIgnoreCase(downloadedFileName))
				{
					flag=true;
					storeDownloadedFile=downloadedFile;
					storeDownloadedFileDateTIme=downloadedFileDateTime;
				}
			}
			if(flag)
			{
				log.info("Download file has been verified -> "+ storeDownloadedFile + " with downloaded date and time " + storeDownloadedFileDateTIme);
				System.out.println("Download file has been verified -> "+ storeDownloadedFile + " with downloaded date and time " + storeDownloadedFileDateTIme);
			}
			else
			{
				log.error("Download file has not been verified" + storeDownloadedFile);
				System.out.println("Download file has not been verified" + storeDownloadedFile);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return flag;
	}

}
