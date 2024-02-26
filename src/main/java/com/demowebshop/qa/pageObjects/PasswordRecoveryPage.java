package com.demowebshop.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordRecoveryPage {
WebDriver driver;
	@FindBy(id="Email")
	private WebElement validPwdToPwdRecovery;
	
	@FindBy(name="send-email")
	private WebElement RecoverButton;
	
	@FindBy(xpath="//div[@class='page-body']/div")
	private WebElement SuccessMsg;
	
	public PasswordRecoveryPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterValidEmailToRecover(String Email)
	{
		validPwdToPwdRecovery.sendKeys(Email);
	}
	
	public void clickOnRecoverButton()
	{
		RecoverButton.click();
	}
	
	public void RecoverPassword(String Email)
	{
		validPwdToPwdRecovery.sendKeys(Email);
		RecoverButton.click();
	}
	
	public String checkSuccessMessage()
	{
		return SuccessMsg.getText();
	}
}
