package com.mdm.seqrite.utils;

import com.google.common.collect.ImmutableMap;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import seqrite.mdm.client.utils.ClientAppUtils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeSuite;



public class SetUp {

		public static ConfigReader configReader = null;
		private String browserName = null;
		protected static WebDriver driver = null;
		public 	static ORReader OR = null;
		public static ClientAppUtils clientApp = null;
		public 	static AndroidORReader AOR = null;
		public static ExcelUtils excelUtils;
		public static AndroidDriver<MobileElement> androidDriver;
		
		public static String androidDevice = null;
		public static String androidVersion = null;
		public static String appPackageName = null;
		public static String androidServer = null;
		public static  String client_automation_flag = null;
		
		
// @BeforeTest(alwaysRun = true)
// public void initializeBrowser() throws Exception {
//	 if(configReader == null) {
//		  configReader = new ConfigReader();
//		  client_automation_flag = configReader.get_Client_Automation_Flag();
//		  Log.info("Client Automation required ? ----> "+client_automation_flag );
//	  }
//	  if(OR == null) {
//		  OR = new ORReader();
//	  }
//	  excelUtils = new ExcelUtils();
//	  clientApp = new ClientAppUtils();
//	  initialization();
//	  Login.loginIntoMDM();
//	  Waits.waitForPageLoaded(Constants.WAIT_INTERVAL);
//	  Waits.waitUntilJqueryIsDone(Constants.WAIT_INTERVAL);
// }
		
  @BeforeMethod(alwaysRun = true) 
  public void initializeMethod(Method method) throws Exception {
	  if(configReader == null) {
		  configReader = new ConfigReader();
		  //client_automation_flag = configReader.get_Client_Automation_Flag();
		  Log.info("Client Automation required ? ----> "+client_automation_flag );
	  }
	  if(OR == null) {
		  OR = new ORReader();
	  }
	  excelUtils = new ExcelUtils();
	  clientApp = new ClientAppUtils();
	  initialization();
	  Log.info("**********************************************************");
	  Log.info("**********TEST CASE :"+method.getName()+"*****************" );
	  
	  
  }

  @AfterMethod(alwaysRun = true) 
  public void endMethod(ITestResult result) throws IOException {
	    result.getTestContext().getSkippedTests().removeResult(result.getMethod());
	  	Process exec = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /t");
		exec.destroy();
		exec = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /t");
		exec.destroy();
    	driver.quit();
		Log.info("***************** Test Ended *****************");
		Log.info("**********************************************************");
  }
  
//  @AfterTest(alwaysRun = true)
//  public void endProcess() throws IOException {
//	  
//	 Process exec = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /t");
//	 exec.destroy();
//	 exec = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /t");
//	 exec.destroy();
//	 driver.quit();	
//  }

  @BeforeSuite(alwaysRun = true) 
  public void onStart() {
	 // DOMConfigurator.configure(System.getProperty("user.dir")+LOG4J_CONFIGURATION_FILE);
  }

  public void initialization() throws IOException {
	  
	  browserName = configReader.getbrowser();
	  
	  if(browserName!=null) {
		  switch (browserName) {
		  case "Chrome":
			  try {
//				  System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
//				  WebDriverManager.chromedriver().setup();
				 
				  driver = new ChromeDriver();
				  
				  Log.info("browserName "+browserName);
				  driver.manage().window().maximize();

			  }catch (WebDriverException e) {
				  	Process exec = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
					exec.destroy();
					exec = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
					exec.destroy();
					//WebDriverManager.chromedriver().setup();
						 
					driver = new ChromeDriver();
					driver.manage().window().maximize();

			  }
		  }

	  }
  }
  
 

}