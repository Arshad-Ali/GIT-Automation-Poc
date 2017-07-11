package com.xap.assertion;

public class ExtraMethods {
	
	//To Extract class name from Testcase that include class with package.
	public static String substring(String str)
	{
		String []s1=str.split("\\.");
		int a=s1.length;
		System.out.println(s1[a-1]);
		return s1[a-1];
	}
}
