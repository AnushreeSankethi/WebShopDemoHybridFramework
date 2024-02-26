package com.demowebshop.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
WebDriver driver;
	@FindBy(className="email")
	private WebElement EmailField;
	
	
	@FindBy(className="password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value='Log in']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[@class='validation-summary-errors']/span")
	private WebElement InvalidFieldErrorMsg;
	
	@FindBy(linkText="Forgot password?")
	WebElement ForgotPassword;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public void ClickOnLoginLink()
	{
		LoginButton.click();
	}
	
	public void login(String Email,String Password)
	{
		EmailField.sendKeys(Email);
		PasswordField.sendKeys(Password);
		LoginButton.click();
	}
	
	public String GetTextOfErrorMsgonInvalidField()
	{
		return InvalidFieldErrorMsg.getText();
	}
	
	public PasswordRecoveryPage ClickOnForgotPwdLink()
	{
		ForgotPassword.click();
		return new PasswordRecoveryPage(driver);
	}
}
