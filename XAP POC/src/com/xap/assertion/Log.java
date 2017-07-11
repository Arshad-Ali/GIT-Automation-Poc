package com.xap.assertion;

import org.apache.log4j.Logger;


public class Log {
	//Initialized log4j
	private static Logger log;
	
	//To print Beginning of the test case.
	public static void starttestcase(String Testccasename)
	{
	log=Logger.getLogger(Testccasename);
	log.info("*****  Starting the test case ***********");
	log.info("$$$$$$$  "+Testccasename+ "   $$$$$$$$$$$$$$$$$$");
	}

	
	//End the TESTCASE
	public static void endtestcase(String Testccasename)
	{
	log.info("*****  Ending the test case ***********");
	log.info("$$$$$$$$$     "+Testccasename+ " $$$$$$$$$$$$$$$$$$");
	}
	//Debug Method
		public static void debug(String msg)
		{
			log.debug(msg);
		}
		//Error Method
		public static void error(String msg)
		{
			log.error(msg);
		}
	
	//Info Method
	public static void info(String msg)
	{
		log.info(msg);
	}
	//trace Method
		public static void trace(String msg)
		{
			log.trace(msg);
		}
		
		//Warn Method
		public static void warn(String msg)
		{
			log.warn(msg);
		}

}
