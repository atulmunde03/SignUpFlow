package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

		  	private Properties pro;

		  	public ConfigReader() {
		  		try {

		  			File config_src = new File(System.getProperty("user.dir")+"//src//resource//java//com//mdm//seqrite//configurations//environmentVariables.property");
		  			FileInputStream appconfig_fis = new FileInputStream(config_src);
		  			pro = new Properties();
		  			pro.load(appconfig_fis);

		  		} catch (IOException e) {
		  			e.printStackTrace();
		  		}
		  	}
		  	
		  //----------------------------------------------------------------------------------------------------------------------------------------------------------------
		  	
		  	public String getbrowser()
		  	{
		  		String browser= pro.getProperty("browserName");
		  		return browser;
		  	}
		  	

			
			public  String getProject_url(){
		  		String PRODMDM_AutomationServer_url=pro.getProperty("MDM_Login_URL");
		  		return PRODMDM_AutomationServer_url;
		  		
		  	}
		  	
	
		  	
		  }

		  
