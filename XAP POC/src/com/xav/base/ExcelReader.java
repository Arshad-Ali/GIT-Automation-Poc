package com.xav.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	public static XSSFWorkbook workbook=null;
	public static XSSFSheet sheet=null;
	public static XSSFRow row=null;
	public static XSSFCell cell=null;
	public static int totalrow;
	public static int totalcell;
	public static void getexcel(String path,String sheetname) throws FileNotFoundException,Exception
	{
		try{
			FileInputStream fin=new FileInputStream(path);
			workbook=new XSSFWorkbook(fin);
			sheet=workbook.getSheet(sheetname);
			totalrow=sheet.getLastRowNum();
			totalcell=sheet.getRow(0).getLastCellNum();
		}
		catch(Exception E)
		{
			System.out.println("Error In Excel Get-" +E);
		}
	}
	
	public static String getcelldata(int rownum,int cellnum)
	{
		cell=sheet.getRow(rownum).getCell(cellnum);
		String celldata=cell.getStringCellValue();
		return celldata;
	}

}
