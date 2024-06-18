package com.utils;

import java.io.IOException;



import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;






public class TestListener extends ReportUtils  implements ITestListener,	ISuiteListener 	{
	
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportElement();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Case PASSED IS " +result.getTestClass()+"_"+result.getName());
	}
	
	//For parallel execution
/*public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
		
		WebDriver driver = null ;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Exception e)
		{
			Log.info("Exception occured");
	}
		try {
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), result.getMethod().getMethodName());
			extentTest.get().log(Status.FAIL, "TEST CASE FAILED IS "+ result.getTestClass()+"_"+result.getName()); 
			
			 String screenshotPath = CommonUtils.getScreenshot(result.getName());
				extentTest.get().fail("Test case snapshot : " +extentTest.get().addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			Log.info("Failed to Capture Screenshot");
			e.printStackTrace();
		}
	}*/


	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
//		try {
//			
//			String screenshotPath = CommonUtils.getScreenshot(result.getName());
//				extentTest.get().fail(" Failed Test case snapshot : " +extentTest.get().addScreenCaptureFromPath(screenshotPath));
//		} catch (IOException e) {
//			Log.info("Failed to Capture Screenshot");
//			e.printStackTrace();
//		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		Log.info("============================================================================");
		Log.info("Starting Test Case ---> "+context.getName());
		
	}

	public void onFinish(ITestContext context) {
		Log.info("Suite : "+context.getName()+" ended");
		Log.info("============================================================================");		
		extent.flush();
	}

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}



}