package com.xav.pom;

import static org.testng.Assert.assertFalse;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.xap.assertion.Log;
import com.xap.assertion.Screenshort;

public class PomLogin {

	public WebDriver driver;
	public Properties locpro;
	
	//constructor to initialized driver and properties that we are accessing from login main class
	
	public PomLogin(WebDriver driver,Properties locpro)
	{
		this.driver=driver;
		this.locpro=locpro;
	}
	
	//methods for all locators
	
		public void username(String uname)
		{
			driver.findElement(By.id(locpro.getProperty("loginnameid"))).sendKeys(uname);
			
		}
		
		public void password(String pwd)
		{
			driver.findElement(By.id(locpro.getProperty("loginpasswordid"))).sendKeys(pwd);
		}
		
		public void loginbtnclk()
		{
			driver.findElement(By.id(locpro.getProperty("loginsubmitid"))).click();	
				Log.info("Passing username and password");
				
			}	
			
	}
 


