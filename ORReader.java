package com.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



	/**
	 * @author siddhant.raut This class is written to read objects from object
	 *         repository
	 */

 public class ORReader extends SetUp {

	protected static Properties prop;
	static WebElement element;
	By lo;

	/**
	 * @author siddhant.raut To retrieve objects from our newly created
	 *         ORReader, we will define an ORReader with a constructor.
	 */

 public ORReader() {
		try {
			File OR_src = new File("./src/resource/java/com/mdm/seqrite/configurations/object_repo.property");

			FileInputStream OR_fis = new FileInputStream(OR_src);
			prop = new Properties();
			try {
				prop.load(OR_fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

 
	/**
	 * @author siddhant.raut This method returns a By object
	 *         that is used by the Selenium browser driver object
	 */
                            //add user btn
	public By getlocator(String strElement) throws Exception {
		String locator = prop.getProperty(strElement);
		
		String locatorType = locator.split("->")[0];    
		String locatorValue = locator.split("->")[1]; 

		// for testing and debugging purposes
		Log.info("Retrieving object " +strElement  +" type '" +locatorType + "' & value '" + locatorValue	+ "' from OR");

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
	 * @author siddhant.raut This method (is_element_present), which returns a WebElement
	 *         by verifying is element is enabled & is element display
	 * @throws Exception 
	 */
	                                            //ADD_USERS_BUTTON
	public WebElement is_element_present(String strElement) throws Exception {
		Thread.sleep(1000);
//		Waits.waitForPageLoaded(Constants.WAIT_INTERVAL);
//		Waits.waitUntilJqueryIsDone(Constants.WAIT_INTERVAL);  
		By locator = getlocator(strElement);
		try {
			
			// Implicit wait implicit wait is to tell WebDriver to poll the DOM
			// for a certain amount of time when trying to find an element
			// or elements if they are not immediately available.
			
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
			element = driver.findElement(locator);
			//CommonUtils.scrolluntilElementIsVisible(element);
			
			Log.info(strElement + " Element is found");
			Boolean isEnabled = element.isEnabled();
			Boolean isDisplayed = element.isDisplayed();		
			if (isEnabled) {
				// Log.info(strElement + " Element is Enabled");
				Log.debug(strElement + " Element is Enabled");

			} else {
				Log.info(strElement + " Element is not Enabled");
			}

			if (isDisplayed) {
				// Log.info(strElement + " Element is Displayed");
				Log.debug(strElement + " Element is Displayed");

			} else {
				Log.info(strElement + " Element is not Displayed");
			}

		} catch (NoSuchElementException e) {

			Log.info(strElement + " Element is not found");
			throw e;
		}		
		return element;
	}


//==========================================Select Value From Dropdown=============================================================
	/**
	 * @author siddhant.raut This method will select the element dropdown value
	 *         To use this method pass the String ddlist [dropdown list Object &
	 *         ddVlaue [dropdown value] to be select]
	 * @throws Exception
	 */

	public void selectValueFromDropdown(String ddList, String ddValue) throws Exception {
		
//		Waits.waitForPageLoaded(Constants.WAIT_INTERVAL);
//		Waits.waitUntilJqueryIsDone(Constants.WAIT_INTERVAL);
		List<WebElement> dropdown_list = null;

		Log.info("Dropdown Value to select is " + ddValue);

		By locator = getlocator(ddList);

		try {
		Log.info("in try block");
			dropdown_list = driver.findElements(locator);
		}

		catch (NoSuchElementException e) {
			Log.info("in catch block");
			Log.info(ddList + " Element is not found");
			//throw e;
		}

		Log.info("Number of Values in dropdown list is: " +dropdown_list.size());
		
		for (int i = 0; i < dropdown_list.size(); i++) {

			WebElement element = dropdown_list.get(i);
			
			String textContent = element.getText();
			
			Log.info("Values from dropdown is: " +textContent);
			
			if (textContent.trim().contentEquals(ddValue.trim())) {
				
				element.click();
				
				Log.info("Clicked On Dropdown Element" +textContent);
				break;
			}

			
		}
						
			Log.info("Values to be selected is: " +ddValue);
		}

	
	
//====================================================================================================================================
	/**
	 * @author siddhant.raut 
	 * 	This method will find multiple objects on and will click on all located objects.
	 *  
	 */
	public void selectCheckBox(String checkbox) throws Exception{

		List<WebElement> apps= null; 
		By locator= getlocator(checkbox);

			try{
				apps= driver.findElements(locator);
				
			    }
			
		catch (NoSuchElementException e) {

			Log.info(checkbox + " Element is not found");
			throw e;
		}
		
		int noOfCheckBoxes= apps.size();
		System.out.println(noOfCheckBoxes);
	
		for(int i=0; i<noOfCheckBoxes; i++)
		{
			WebElement element= apps.get(i);
			element.click();
			String textContent=element.getAttribute("textContent");
			Log.info("CheckBox: " +textContent );
		}
	}
//===================================================================================================================================
	/**
	 * @author siddhant.raut 
	 * 	This method identifies the element on the page and perform the click action.
	 */
	
	public void clickOnElement(String strElement) throws Exception{			
	
	element=is_element_present(strElement);
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().build().perform();
	}
//===================================================================================================================================

	
	public String[] selectdevicestatusKey(String stsNameKey) throws Exception{		
		List<WebElement> status_list = null;

	By locator = getlocator(stsNameKey);

	try {

			status_list = driver.findElements(locator);
		}

		catch (NoSuchElementException e) {

			Log.info(stsNameKey + " Element is not found");
			throw e;
		}

	Log.info("Number of Values to be verified on devices screen is: " +status_list.size());
	    WebElement element;
		String textContent = null;
		String keydata[]=new String[status_list.size()];
		
		
		for (int i = 0; i < status_list.size(); i++) {

			element = status_list.get(i);
			textContent = element.getText();
		
				keydata[i]=textContent;	
			
	}
	
		return keydata;
		
		
		
	}
	
	
	
	
	//==================================================================================================
	
	public String[] selectdevicestatusValue(String stsNameVal) throws Exception{		
		List<WebElement> status_list = null;

	

	By locator = getlocator(stsNameVal);

	try {

			status_list = driver.findElements(locator);
		}

		catch (NoSuchElementException e) {

			Log.info(stsNameVal + " Element is not found");
			throw e;
		}

	Log.info("Number of Values to be verified on devices screen is: " +status_list.size());
	    WebElement element;
		String textContent = null;
		String valuedata[]=new String[status_list.size()];
		
		
		for (int i = 0; i < status_list.size(); i++) {

			element = status_list.get(i);
			textContent = element.getText();
		
				valuedata[i]=textContent;	
			
	}
	
		return valuedata;
		
		
		
	}
    
	
//===================================================================================================================================
	/**
	 * @author Lavina jain This method identifies the element on the page and
	 *         perform the click action.
	 */

	public void hoverOnElement(String strElement) throws Exception {

		element = is_element_present(strElement);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}
//==========================================================================================================================

	/**
	 * @author Pooja Ainarkar This method identifies the new pop up window and
	 *         performs action on it.
	 */

	public void popuphandle(String strElement, String value) throws Exception {
		Actions act = new Actions(driver);
		WebElement ele = is_element_present(strElement);
		act.moveToElement(ele);
		act.click();
		act.sendKeys(value);
//		Waits.waitForPageLoaded(Constants.WAIT_INTERVAL);
//		Waits.waitUntilJqueryIsDone(Constants.WAIT_INTERVAL);
		act.build().perform();
	}

//==========================================================================================================================

	/**
	 * @author Lavina Jain
	 * This method which finds the list of web elements & returns its size by findElements() function
	 * @throws Exception 
	 */

	public int find_elements_list(String strElement) throws Exception {
		List<WebElement> list =new ArrayList<>();
		int size_list=0;
		By locator = getlocator(strElement);			
		try {
			
			list = driver.findElements(locator);
			size_list=list.size();
			Log.info("Number of Values in list is: " +size_list);
			}

		catch (NoSuchElementException e) {

			Log.info(list+ " Element is not found");
			throw e;
			}		
			return size_list;
	}
	
	//===============================================================
	/** @author pratik.raushan
	 * Find webElements and return list
	 * @throws Exception 
	 */

	public  List<WebElement> findListOfWebElements(String strElement) throws Exception {
		
		By locator = getlocator(strElement);
		
		List <WebElement> listOfWebElement = driver.findElements(locator);
		
		if(listOfWebElement.size()<1) {
			Log.info("Element List not found");
			
		}
		return listOfWebElement;
	}
	
/**
 * @author Priyanka.Kharche
 * scroll page to top or end
 */
	public void scrollPage(String to) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (to.equals("end")) {
			executor.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scollHeight,document.documentElement.clientHeight));");}

		else if(to.equals("top"))
		{
			executor.executeScript(
	
					"window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scollHeight,document.documentElement.clientHeight),0);");
		}else {
			throw new Exception("Exception:Invalid direction only scroll top and end");}
}
	
 }
 