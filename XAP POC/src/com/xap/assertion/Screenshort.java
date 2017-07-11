package com.xap.assertion;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshort {
public static void takescreenshot(WebDriver driver,String Status,String ClassName,Properties conpro) throws IOException
{
	
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	DateFormat DateFormat=new SimpleDateFormat("MMMM-dd-yyyy HH-mm-ss z");
	Date  date=new Date();
	String dest=System.getProperty("user.dir")+conpro.getProperty("ScreenShotPath")+Status+"\\"+ClassName+" "+DateFormat.format(date)+".png";
	File destination=new File(dest);
	FileUtils.copyFile(src, destination);
}
}
