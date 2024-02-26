package com.demowebshop.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
WebDriver driver;
	
	@FindBy(linkText="Elite Desktop PC")
	private WebElement ProductDisplay;

	@FindBy(className="result")
	private WebElement ErrorMsg;
	
public SearchPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

//Actions
public boolean SearchForEnteredProduct()
{
	boolean displayStatus=ProductDisplay.isDisplayed();
	return displayStatus;
}



public String GetErrorMessageForInvalidProduct()
{
	return ErrorMsg.getText();
}
}