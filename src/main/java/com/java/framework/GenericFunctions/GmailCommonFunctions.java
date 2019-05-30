package com.java.framework.GenericFunctions;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.java.framework.Utils.EnvironmentPropertiesReader;
import com.java.framework.Utils.GenericUtils;

public class GmailCommonFunctions {

	By confirmYourWithdrawLink = By.xpath("//a[contains(text(),'apexwebqa.')]");
	By googleAccount = By.xpath("//a[contains(@aria-label,'Google Account')]");
	By signOutButton = By.xpath("//a[text()='Sign out']");
	By userNameField = By.cssSelector("input[id='identifierId']");
	By userNameNextButton = By.cssSelector("div#identifierNext content>span.RveJvd.snByac");
	By passwordField = By.cssSelector("input[name='password']");
	By passwordNextButton = By.cssSelector("div#passwordNext content span.RveJvd.snByac");
	By listOfEmails = By.cssSelector("div.Cp tr");

	public Properties prop;

	public void GmailLogin(WebDriver driver, String gmailUserName, String password, String subject) throws Exception {
		//String linkText = null;
		//String subjectText;		
		GenericUtils objGenericUtils = new GenericUtils(driver);
		prop=EnvironmentPropertiesReader.getInstance().PropertiesFile();
		try {			
			objGenericUtils.TurnOnImplicitWaits();
			objGenericUtils.EnterTextUsingActions(driver.findElement(userNameField), gmailUserName);
			objGenericUtils.WaitForElementToBeClickable(driver.findElement(userNameNextButton));
			GenericUtils.EnterText(driver.findElement(passwordField), password);
			objGenericUtils.WaitForElementToBeClickable(driver.findElement(passwordNextButton));			
			/*List<WebElement> listOfElement = driver.findElements(listOfEmails);
			for (int i = 0; i < listOfElement.size(); i++) {
				Iterator<WebElement> iterate = listOfElement.iterator();
				while (iterate.hasNext()) {
					WebElement element = iterate.next();
					subjectText = element.getText();

					if (subjectText.equalsIgnoreCase(subject)) {
						objGenericUtils.WaitForElementToBeClickable(element);
						break;
					}
				}
			}			
			linkText = driver.findElement(confirmYourWithdrawLink).getText();
			return linkText;
		} catch (StaleElementReferenceException e) {
			linkText = driver.findElement(confirmYourWithdrawLink).getText();
			return linkText;
		}*/
			Thread.sleep(3000);
			if(driver.getCurrentUrl().equalsIgnoreCase(prop.getProperty("GmailLoggedInURL")))
			{
				System.out.println("User sign in successfully....");
			}
			else
			{
				System.out.println("User Sign in failed...");
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	public void GmailLogOut(WebDriver driver) throws Exception {
		
		GenericUtils objGenericUtils = new GenericUtils(driver);		
		prop=EnvironmentPropertiesReader.getInstance().PropertiesFile();
		
		try {
			objGenericUtils.WaitForElementToBeClickable(driver.findElement(googleAccount));
			objGenericUtils.WaitForElementToBeClickable(driver.findElement(signOutButton));
			Thread.sleep(3000);
			if(driver.getCurrentUrl().equalsIgnoreCase(prop.getProperty("GmailLoggedoutURL")))
			{
				System.out.println("User sign Out successfully....");
			}
			else
			{
				System.out.println("User Sign out failed...");
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
