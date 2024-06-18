package com.appmodule;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import com.mdm.seqrite.utils.Log;

import com.mdm.seqrite.utils.ORReader;
import com.mdm.seqrite.utils.Waits;

public class signUpflow  extends ORReader{
	
	
	public String checkSignUpCreateAcc() {
		try {
			
			driver.get(configReader.getProject_url());
			Log.info("get url");

			OR.is_element_present("CREATE_ACCOUNT_BUTTON").click();
			
			OR.is_element_present("FIRST_NAME_TEXTBOX").sendKeys("Atul");
			
			OR.is_element_present("LAST_NAME_TEXTBOX").sendKeys("munde");
			
			WebElement element = driver.findElement(By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			
			OR.is_element_present("EMAIL_ADD_TEXTBOX").sendKeys("afghj@gmail.com");
			
			OR.is_element_present("PASSWORD_ADD_TEXTBOX").sendKeys("Quick@123");
			
			OR.is_element_present("PASSWORD_SIGNUP_CONFIRMATION_TEXTBOX").sendKeys("Quick@123");
			
			OR.is_element_present("CREATE_ACC_CONFIRMATION_TEXTBOX").click();
	
			
	
		} catch (Exception e) {
			Log.info("Test case failed with exception" + e);
		}

		return "ok";
	}
	
	public String checkSignInPage() {
		try {
			
			driver.get(configReader.getProject_url());
			Log.info("get url");
			
			OR.is_element_present("SIGNOUT_DROPDOWN").click();

			OR.is_element_present("SIGNOUT_BUTTON").click();
			
			OR.is_element_present("SIGNIN_BUTTON").click();
			
			OR.is_element_present("EMAIL_TEXTBOX").sendKeys("afghj@gmail.com");
			
			OR.is_element_present("PASSWORD_SIGNIN_TEXTBOX").sendKeys("Quick@123");
			
			OR.is_element_present("SIGNIN_PORJECT_BUTTON").click();
	
			
	
		} catch (Exception e) {
			Log.info("Test case failed with exception" + e);
		}

		return "ok";
	}


}
