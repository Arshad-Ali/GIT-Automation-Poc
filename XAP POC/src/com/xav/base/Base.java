/**
 * 
 */
package com.xav.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.xap.assertion.ExtraMethods;
import com.xap.assertion.Log;
import com.xap.assertion.Screenshort;

/**
 * @author Arshad Git Project
 *
 */
public class Base extends ExcelReader {
public WebDriver driver;
public Properties locpro,conpro;
public  String browser=null;
public String siteurl=null;
public String chromepath=null;
public Object[][] Userdata=null;
public String parentclass;

/* get webdriver */
public WebDriver getdriver()
{
	return driver;
}

/*read properties file*/

public void readproperties() throws FileNotFoundException
{
	File confile=new File(System.getProperty("user.dir")+"//Properties//Config.properties");
	File locfile=new File(System.getProperty("user.dir")+"//Properties//locators.properties");
	FileInputStream conis=new FileInputStream(confile);
	FileInputStream locis=new FileInputStream(locfile);
	try
	{
		locpro=new Properties();
		conpro=new Properties();
		locpro.load(locis);
		conpro.load(conis);
	}
	catch(Exception e)
	{
		System.out.println("Error in properties file: "+ e);
	}
	
}

/* ****** TO check Browser*******  */

public void setbrowser(String browser,String siteurl) 
{
	
	switch(browser){
	case "chrome":
		driver=initchromebrowser(siteurl);
		break;
	case "firefox":
		driver=initfirefoxbrowser(siteurl);
		break;
/*	case "exprorer":
		driver=initexprorerbrowser(siteurl);
		break;*/
	default:
		System.out.println("Invalid browser,launcching firefox as default");
		driver=initfirefoxbrowser(siteurl);
	}	
}
/* ******initialize browsers ********** */

public WebDriver initchromebrowser(String url) 
{
	chromepath=conpro.getProperty("chromebrowserpath");
	System.setProperty("webdriver.chrome.driver",chromepath);
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(url);
	return driver;
}

public WebDriver initfirefoxbrowser(String url)
{
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get(url);
	return driver;
}
/* ************************** */
public void setparentclass(String classname)
{
	parentclass=ExtraMethods.substring(classname);
}

/* before method */

@BeforeMethod
public void base() throws Exception
{
	try{
	//To set logfile path ie.log.xml path
	DOMConfigurator.configure(conpro.getProperty("Logfile"));
	browser=conpro.getProperty("browser");
	siteurl=conpro.getProperty("siteurl");
	setbrowser(browser, siteurl);
	}
	catch(Exception e)
	{
		System.out.println("Error in before method" +e);
	}

}

@DataProvider(name = "TestDataProvider")
public Object[][] dataprovider() throws FileNotFoundException, Exception
{
	readproperties();
	getexcel(conpro.getProperty("TestCaseSheetPath"), "Login Test Data");
	System.out.println("Total no. of rows and Column are: " +totalrow +totalcell);
	Userdata=new Object[totalrow][totalcell];
	
	//System.out.println("Data In excel: "+getcelldata(0, 0) +"And"+ getcelldata(0, 1));
	System.out.println("user data array: Row= "+Userdata.length+" Col= "+Userdata[0].length);
	for(int i=0;i<totalrow;i++)
	{
		for(int j=0;j<totalcell;j++)
		{
			Userdata[i][j]=getcelldata(i+1,j);
			System.out.println("Data "+i  + j +" Sheetdata "+ Userdata[i][j]);
		}
	}
	
	return Userdata;
}

@AfterMethod
public void after(ITestResult result) throws IOException
{
	//Take Screenshot for Filed test case.
	/*Generally, scripts fail in 2 situations.
1-If script has some issue (some locator has been changed or application has some changes)- In this case, we need to maintain our Selenium script.
2-Due to application issue- In this case, we need to inform to respective point of contact (Manual Tester or Developer)
*/
	if(ITestResult.FAILURE==result.getStatus())
	{
		try {
			Screenshort.takescreenshot(driver, "Fail", parentclass, conpro);
			Log.error("Failure in class "+parentclass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("Error to take ScreenShot");
		}
	}
	else
	{
		Screenshort.takescreenshot(driver, "Pass", parentclass, conpro);
		Log.info("Pass"+parentclass+"TestCase");
		Log.endtestcase(parentclass);
	}
		
	driver.quit();
}
}
