package com.java.framework.TestClasses;

import org.testng.annotations.Test;

import com.java.framework.BaseClass.AdminPortalTestBase;

public class AdminTest extends AdminPortalTestBase {
	
	AdminPortalTestBase objAdminPortalTestBase;
	
	@Test	
	public void Login()
	{
		System.out.println("Login using Admin");
	}
}
