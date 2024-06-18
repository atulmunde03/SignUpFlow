package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.MobileElement;


//import org.openqa.selenium.support.ui.Clock;


/**
 * @author omkar.naik This class is written to read objects from object
 *         repository for android application
 */

public class AndroidORReader extends SetUp{
	
	public static Properties AOR;
	public static MobileElement mobelement;
	By alo;
	
	
	/**
	 * @author omkar.naik To retrieve objects from our newly created
	 *         AORReader, we will define an AORReader with a constructor.
	 */
	


	 /**
		 * @author omkar.naik This method returns a By object
		 *         that is used by the Android device driver object
		 */	 
	 
	 public By getlocator(String astrElement) throws Exception {
			String locator = AOR.getProperty(astrElement);
			
			String locatorType = locator.split("->")[0];
			String locatorValue = locator.split("->")[1];

			// for testing and debugging purposes
			Log.info("Retrieving object " +astrElement  +" type '" +locatorType + "' & value '" + locatorValue	+ "' from AOR");

			// return a instance of the By class based on the type of the locator
			// this By can be used by the browser object in the actual test
				
			if (locatorType.toLowerCase().equals("id"))
				return By.id(locatorValue);
			else if (locatorType.toLowerCase().equals("name"))
				return By.name(locatorValue);
			else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
				return By.className(locatorValue);
			else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
				return By.className(locatorValue);
			else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
				return By.linkText(locatorValue);
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return By.partialLinkText(locatorValue);
			else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
				return By.cssSelector(locatorValue);
			else if (locatorType.toLowerCase().equals("xpath"))
				return By.xpath(locatorValue);
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	 
	 /**
		 * @author omkar.naik This method (is_element_present), which returns a AndroidElement
		 *         by verifying is element is enabled & is element display
		 * @throws Exception 
		 */	 
	 
	 public MobileElement is_element_present(String astrElement) throws Exception {

			By locator = getlocator(astrElement);
			try {

				// Implicit wait implicit wait is to tell WebDriver to poll the DOM
				// for a certain amount of time when trying to find an element
				// or elements if they are not immediately available.
					
				if(!(androidDriver == null)){
	
				WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.elementToBeClickable(locator));	
				}
				
				mobelement = androidDriver.findElement(locator);
				Log.info(astrElement + " Element is found");
				

			} catch (NoSuchElementException e) {

				Log.info(astrElement + " Element is not found");
				throw e;
			}

			Boolean isEnabled = mobelement.isEnabled();
			Boolean isDisplayed = mobelement.isDisplayed();

			if (isEnabled) {
			
				Log.debug(astrElement + " Element is Enabled");

			} else {
				Log.info(astrElement + " Element is not Enabled");
			}

			if (isDisplayed) {
				// Log.info(strElement + " Element is Displayed");
				Log.debug(astrElement + " Element is Displayed");

			} else {
				Log.info(astrElement + " Element is not Displayed");
			}
			return mobelement;
	 }
	 
	 
	public List<MobileElement> findMobileElementList(String mobileElementLocator) throws Exception {
		
		By locator = getlocator(mobileElementLocator);
		
		List<MobileElement> mobileElementsList = androidDriver.findElements(locator);
		
		if (mobileElementsList.size() > 0) {
			
			Log.info("mobile element list found ");
		}
		
		return mobileElementsList;
		
	}
	 
	 
	 
	 
	 
	 
}

