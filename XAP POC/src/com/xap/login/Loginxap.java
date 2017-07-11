package com.xap.login;

import static org.testng.Assert.assertFalse;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.xap.assertion.ExtraMethods;
import com.xap.assertion.Log;
import com.xap.assertion.Screenshort;
import com.xav.base.Base;
import com.xav.pom.PomLogin;

public class Loginxap extends Base {
	public WebDriver driver;
	private String Testcasename;
	/*@BeforeClass
	public void testsetup()
	{
		driver=getdriver();
	}*/
	@Test(dataProvider="TestDataProvider")
	public void testmethod(String username,String Password) throws InterruptedException, IOException
	{
		
		Testcasename=this.getClass().getName();
		String testclass=ExtraMethods.substring(Testcasename);
		Log.starttestcase(testclass);
		Log.info("Test Case Start");
		driver=getdriver();
		setparentclass(Testcasename);
		/*driver.findElement(By.id(locpro.getProperty("loginnameid"))).sendKeys(conpro.getProperty("username"));
		driver.findElement(By.id(locpro.getProperty("loginpasswordid"))).sendKeys(conpro.getProperty("password"));*/
		
		//using POM
		PomLogin pomlogin=new PomLogin(driver,locpro);
		pomlogin.username(username);
		pomlogin.password(Password);
		pomlogin.loginbtnclk();
		assertFalse(driver.getPageSource().contains("Invalid User Name or Password. Please enter correct User Name and Password."));
		
	}

}
