package com.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits  extends SetUp {
	
	
	public static ExpectedCondition<Boolean> expectation;
	public static WebDriverWait wait = null;
	
	public static void waitForPageLoaded(int timeInterval) {
        expectation = new ExpectedCondition<Boolean>() {
        			@Override
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
        };
        wait = new WebDriverWait(driver,Duration.ofSeconds(timeInterval));
        wait.until(expectation);
        
	}
	
	public static void waitUntilJqueryIsDone(int timeInterval) {
        expectation = new ExpectedCondition<Boolean>() {
        			@Override
                    public Boolean apply(WebDriver driver) {
        				//Added by Akshay
        				
                        return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
        				//return true;
        			}
        };
        wait = new WebDriverWait(driver,Duration.ofSeconds(timeInterval));
        wait.until(expectation);
        
	}
	
	public static void waitForSearch(String element , String expectedValue) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(1000))
				.pollingEvery(Duration.ofMillis(5));
		
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
            public Boolean apply(WebDriver driver) {
				try {
					Log.info("inside waitForSearch");
					return OR.is_element_present(element).getText().trim().equalsIgnoreCase(expectedValue);
				} catch (Exception e) {
					Log.info("Exception Occured in waitForSearch()");
					Log.info("Exception "+e);
					return false;
				}
			}
		});
		
	}
	

}
