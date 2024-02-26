package com.demowebshop.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String GenerateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "demo"+timestamp+"@gmail.com";

	}
	public static Object[][] getTestDataFromExcel(String sheetName )
	{
		XSSFWorkbook workbook=null;
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\demowebshop\\qa\\utils\\LoginTestData.xlsx");
		try {
		FileInputStream excelFis = new FileInputStream(excelFile);
		workbook = new XSSFWorkbook(excelFis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		short cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++)
			{
	            XSSFCell col = row.getCell(j);
	            CellType cellType = col.getCellType();
	            
	            switch(cellType) {
	            case STRING:
	            	data[i][j]=col.getStringCellValue();
	            	break;
	            case NUMERIC:
	            	data[i][j]= Integer.toString((int)col.getNumericCellValue());
	            	break;
	            case BOOLEAN:
	            	data[i][j]=col.getBooleanCellValue();
	            	break;
	            }
			}
		}
		return data;
		
	}
	public static String capturecScreenshot(WebDriver driver, String TestName)
	{
		File SrcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshot=System.getProperty("user.dir")+"\\Screenshots\\"+TestName+".png";
		try {
			FileHandler.copy(SrcScreenshot, new File(destinationScreenshot));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destinationScreenshot;
	}
}
