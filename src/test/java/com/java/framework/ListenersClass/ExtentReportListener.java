package com.java.framework.ListenersClass;

import java.io.File;
import java.util.Date;


import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.java.framework.TestClasses.ExtentReportDemo;

public class ExtentReportListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	
	File file;
	ExtentHtmlReporter e1;
	ExtentReports e2;
	ExtentTest e3;

	ExtentReportDemo exObj;
	
	public void onStart(ISuite suite) {
		exObj=new ExtentReportDemo();
		String reportPath = System.getProperty("user.dir") + "/ExtentReports/"+ exObj.getClass().getName()+ " " + new Date().getTime()+ ".html";
		file = new File(reportPath);
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
	}

	public void onFinish(ISuite suite) {
		e2.flush();
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		e3 = e2.createTest("This is automation test for generating report");

	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		e3.log(Status.INFO, testResult.getMethod().getMethodName() + "module has been started.");
	}

	public void onTestStart(ITestResult result) {

		System.out.println("I am in onTestStart method " + result.getMethod().getMethodName() + " start");
	}

	public void onTestSuccess(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			e3.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
			e3.log(Status.PASS, result.getMethod().getMethodName() + " test passed");
		}
	}

	public void onTestFailure(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
			e3.log(Status.FAIL, result.getMethod().getMethodName() + " test failed");
			e3.log(Status.FAIL, result.getThrowable().getCause());
			e3.log(Status.FAIL, result.getThrowable().getMessage());
		}

	}

	public void onTestSkipped(ITestResult result) {

		if (result.getStatus() == ITestResult.SKIP) {
			e3.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.YELLOW));
			e3.log(Status.SKIP, result.getMethod().getMethodName() + " test skipped");
			e3.log(Status.SKIP, result.getThrowable());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
			e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
			e3.log(Status.FAIL, result.getThrowable());
			e3.log(Status.FAIL, result.getMethod().getMethodName()
					+ " module passed but with on Test Failed But Within Success Percentage.");

		}

	}

	public void onStart(ITestContext context) {

		System.out.println("I am in onStart method " + context.getName());
	}

	public void onFinish(ITestContext context) {

		System.out.println("I am in onFinish method " + context.getName());
	}

}
