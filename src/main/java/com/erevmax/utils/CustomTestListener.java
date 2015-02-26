package com.erevmax.utils;


import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;

//import atu.testng.reports.ATUReports;

public class CustomTestListener extends TestListenerAdapter implements ISuiteListener,ITestListener {
	
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			List<Throwable> verificationFailures = Verify.getVerificationFailures();

			//if there are verification failures...
			if (verificationFailures.size() > 0) {
			//set the test to failed
				result.setStatus(ITestResult.FAILURE);
		//		ATUReports.add("Plz refer the fail screen shot", " ", " "," ",true);
				//if there is an assertion failure add it to verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}
				
				int size = verificationFailures.size();
				//if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
				} else {
					//create a failure message with all failures and stack traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):\n\n");
					for (int i = 0; i < size-1; i++) {
						failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":\n");
						Throwable t = verificationFailures.get(i);
						//String fullStackTrace = Utils.stackTrace(t, true)[1];
						String fullStackTrace = Utils.stackTrace(t, false)[0];						
						failureMessage.append(fullStackTrace).append("\n\n");
					}
					
					//final failure
					Throwable last = verificationFailures.get(size-1);
					failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":\n");
					failureMessage.append(last.toString());
					
					//set merged throwable
					Throwable merged = new Throwable(failureMessage.toString());
					merged.setStackTrace(last.getStackTrace());
					
					result.setThrowable(merged);
				}
			}
		}
	}

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		Reporter.log(result.getName());
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
