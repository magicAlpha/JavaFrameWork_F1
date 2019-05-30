package com.java.framework.TestClasses;
import org.testng.annotations.Test;

public class ExtentReportDemo {
		
	
	@Test(priority=0)
	public void Login()
	{
		System.out.println("Login successful....");
	}
	
	@Test(priority=1)
	public void Register()
	{
		System.out.println("Registration successful....");
	}
	
	@Test(priority=2)
	public void ResetPassword()
	{
		System.out.println("Reset password successful....");
	}
	
	@Test(priority=3)
	public void SearchResult()
	{
		System.out.println("Searched result successful....");
	}

}
