package com.java.framework.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtils {

	WebDriver driver;

	public GenericUtils(WebDriver driver) {
		this.driver = driver;
	}

	// This method will wait until 40 seconds
	public void TurnOnImplicitWaits() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will wait until for few seconds to check the visibility of
	// element and click on it
	public void WaitForElementVisiblity(WebElement webElement, int waitInSeconds) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will wait until for few seconds to click on it
	public void WaitForElementToBeClickable(WebElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will wait until for few seconds to check the presence of element
	// and click on it
	public void WaitForElementToBePresence(By locator) throws Exception {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will select an element based on text
	public void selectElement(WebElement webElement, String selectByVisibleTextValue) {

		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(selectByVisibleTextValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method will perform an click action on element
	public static void Click(WebElement element) {
		try {
			Thread.sleep(1000);
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method will perform an clear action text field element
	public static void Clear(WebElement element) {
		try {
			Thread.sleep(1000);
			element.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method will write text in text box element
	public static void EnterText(WebElement element, String value) {
		try {
			Thread.sleep(1000);
			element.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void EnterTextUsingActions(WebElement element, String text) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(element, text).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public void DoubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.doubleClick(element).build().perform();
		} catch (Exception e) {

		}
	}

	// This method will get all files from folder
	public static ArrayList<String> getfileNamesFromFolder(String path) throws Exception {

		ArrayList<String> listOfFilesArray;
		File folder;
		File[] listOfFiles;
		try {
			listOfFilesArray = new ArrayList<String>();
			folder = new File(path);
			listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					listOfFilesArray.add(listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		} catch (Exception e) {
			throw e;
		}
		System.out.println(listOfFilesArray);
		return listOfFilesArray;
	}

	// This method will scroll to bottom of the page
	public void ActionScrollToBottom() throws Exception {

		try {
			Actions actions = new Actions(driver);
			for (int i = 1; i <= 100; i++) {
				actions.sendKeys(Keys.PAGE_DOWN).perform();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will scroll to top of the page
	public void ActionScrollToTop() throws Exception {

		try {
			Actions actions = new Actions(driver);
			for (int i = 1; i <= 100; i++) {
				actions.sendKeys(Keys.PAGE_UP).perform();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will refresh the page
	public void RefreshPage() throws Exception {

		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will forward to next URL from the current opened page
	public void PageForward() throws Exception {

		try {
			driver.navigate().forward();

		} catch (Exception e) {
			throw e;
		}
	}

	// This method will navigate to particular URL
	public void NavigateToURL(String URL) {
		try {
			driver.navigate().to(URL);
		} catch (Exception e) {

		}
	}

	// This method will scroll to the particular web element
	public WebElement scrollToViewElement(WebElement element) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw e;
		}
		return element;
	}

	// This method will check if element is displayed
	public boolean IsElementDisplayed(WebElement element) throws Exception {
		boolean flag = false;
		try {
			if (element.isDisplayed()) {
				flag = true;
			}
			if (flag) {
				System.out.println("Element is displayed");
			} else {
				System.out.println("Element is not displayed");
			}
		} catch (Exception e) {
			TurnOnImplicitWaits();
		}
		return flag;
	}

	// This method will check if element is enabled
	public boolean IsElementEnabled(WebElement element) throws Exception {
		boolean flag = false;
		try {
			if (element.isEnabled()) {
				flag = true;
			}

			if (flag) {
				System.out.println("Element is enabled");
			} else {
				System.out.println("Element is not enabled");
			}
		} catch (Exception e) {
			TurnOnImplicitWaits();
		}
		return flag;
	}

	// This method will check if element is selected
	public boolean IsElementSelected(WebElement element) throws Exception {
		boolean flag = false;
		try {
			if (element.isSelected()) {
				flag = true;
			}

			if (flag) {
				System.out.println("Element is selected");
			} else {
				System.out.println("Element is not selected");
			}
		} catch (Exception e) {
			TurnOnImplicitWaits();
		}
		return flag;
	}

	// This will return the selected value from SelectDropdown
	public String getSelectedTextFromDropdown(WebElement element) {
		String getSelectedTextValue=null;
		try {

			Select select = new Select(element);
			getSelectedTextValue = select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSelectedTextValue;
	}
	
	public List<String> getAllSelectedOptionFromDropDown(WebElement element)
	{
		List<WebElement> list=null;
		ArrayList<String> textList=new ArrayList<String>();
		int getSize=0;
		String textValue;
		try
		{
			Select select=new Select(element);
			list=select.getOptions();
			getSize=list.size();
			
			for(int i=1;i<=getSize;i++)
			{
				textValue=list.get(i).getText();
				textList.add(textValue);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return textList;
	}

	// This method will calculate the total number of element
	public int CountOfElements(By element) throws Exception {
		int elementsCount;
		try {
			elementsCount = driver.findElements(element).size();
		} catch (Exception e) {
			throw e;
		}

		return elementsCount;
	}

	// This method will calculate the total numbers of window active
	public int CountOfWindows() throws Exception {
		int windowsCount;
		try {

			Set<String> windows = driver.getWindowHandles();
			windowsCount = windows.size();

		} catch (Exception e) {
			throw e;
		}
		return windowsCount;
	}

	// This method will help to switch particular window based on their index number
	// provided
	public void SwitchToWindow(int windowIndexNumber) throws Exception {
		try {
			Set<String> collectedWindows = driver.getWindowHandles();
			String window = collectedWindows.toArray()[windowIndexNumber].toString();
			driver.switchTo().window(window);
		} catch (Exception e) {
			throw e;
		}
	}

	// This method will mouse hover to the element
	public void MouseHoverToElement(WebElement element) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}
}
