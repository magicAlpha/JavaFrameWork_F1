package com.java.framework.ListenersClass;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer {

	int minCount=0;
	int maxCount=3;
	
	public boolean retry(ITestResult result) {
		
		boolean flag=false;
		
		if(minCount<maxCount)
		{
			minCount++;
			 System.out.println("Retrying test " + result.getName() + " with status "
	                    + getResultStatusName(result.getStatus()) + " for the " + (minCount+1) + " time(s).");
			flag=true;
		}
		return flag;		
	}
	
	 public String getResultStatusName(int status) {
	    	String resultName = null;
	    	if(status==1)
	    		resultName = "SUCCESS";
	    	if(status==2)
	    		resultName = "FAILURE";
	    	if(status==3)
	    		resultName = "SKIP";
			return resultName;
	    }

}
