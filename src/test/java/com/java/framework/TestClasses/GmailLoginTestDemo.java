package com.java.framework.TestClasses;

import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.java.framework.BaseClass.GmailTestBase;
import com.java.framework.GenericFunctions.GmailCommonFunctions;
import com.java.framework.Utils.EnvironmentPropertiesReader;

public class GmailLoginTestDemo extends GmailTestBase {

	GmailCommonFunctions objGmailCommonFunctions;	
	public Properties prop;

	@Test(priority = 0)
	public void GmailLogin() {
		String userName;
		String passWord;
		String subject = "[GitHub] A personal access token has been added to your account";
		try {
			prop = EnvironmentPropertiesReader.getInstance().PropertiesFile();
			objGmailCommonFunctions = PageFactory.initElements(driver, GmailCommonFunctions.class);
			userName = prop.getProperty("GmailUserName");
			passWord = prop.getProperty("GmailUserPassword");
			objGmailCommonFunctions.GmailLogin(driver, userName, passWord, subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods="GmailLogin")
	public void GmailLoginout() {
		try {
			objGmailCommonFunctions.GmailLogOut(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
