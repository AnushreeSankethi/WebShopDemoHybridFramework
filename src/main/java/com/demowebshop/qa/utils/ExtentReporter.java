package com.demowebshop.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
 ExtentReports extentReports = new ExtentReports();
 
 File extentFileReport = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
 
 ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentFileReport);
 
 sparkreporter.config().setTheme(Theme.DARK);
 sparkreporter.config().setReportName("demoWebShop Test Automation");
 sparkreporter.config().setDocumentTitle("DWS Automation Report");
 sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
 
 extentReports.attachReporter(sparkreporter);
 
 Properties configProp = new Properties();
 File ConfigFileProp = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\demowebshop\\qa\\config\\config.properties");
 try {
 FileInputStream fisconfigProp = new FileInputStream(ConfigFileProp);
 configProp.load(fisconfigProp);
 }catch (Throwable e) {
	 e.printStackTrace();
 }
 extentReports.setSystemInfo("Application URL",configProp.getProperty("url"));
 extentReports.setSystemInfo("BrowserName", configProp.getProperty("browserName"));
 extentReports.setSystemInfo("Email", configProp.getProperty("validEmailID"));
 extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
 extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
 extentReports.setSystemInfo("Operating System", System.getProperty("user.name"));
 extentReports.setSystemInfo("Operating System", System.getProperty("java.version"));
 return extentReports;
}




}