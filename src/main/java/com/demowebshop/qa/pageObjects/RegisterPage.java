package com.demowebshop.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(id="gender-female")
	private WebElement GenderSelection;
	
	@FindBy(id="FirstName")
	private WebElement FirstName;
	
	@FindBy(id="LastName")
	private WebElement LastName;
	
	@FindBy(name="Email")
	private WebElement Email;
	
	@FindBy(id="Password")
	private WebElement Password;
	
	@FindBy(id="ConfirmPassword")
	private WebElement ConfirmPassword;
	
	@FindBy(xpath="(//input[@type='submit'])[2]")
	private WebElement RegisterBtn;
	
	@FindBy(className="result")
	private WebElement RegisterSuccessmsg;
	
	@FindBy(xpath="//span[text()='Wrong email']")
	private WebElement WrongEmailErrormsg;
	
	@FindBy(xpath="//span[text()='Last name is required.']")
	private WebElement WithoutLastNameErrormsg;
	
	@FindBy(xpath="//span[text()='First name is required.']")
	private WebElement WithoutFirstNameErrormsg;
	
	@FindBy(xpath="//span[text()='Password is required.']")
	private WebElement WithoutConfirmPasswordErrormsg;
	
	@FindBy(xpath="//span[text()='The password and confirmation password do not match.']")
	private WebElement WithoutPasswordErrormsg;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectGender()
	{
		GenderSelection.click();
	}
	
	
	
	public boolean VerifyRegistrationSuccessMsg()
	{
		boolean statusDisplay = RegisterSuccessmsg.isDisplayed();
		return statusDisplay;
	}
	
	public String VerifyErrorMsgForWrongEmail()
	{
		return WrongEmailErrormsg.getText();
	}
	public String VerifyErrorMsgForNotEnteringLastName()
	{
		return WithoutLastNameErrormsg.getText();
	}
	public String VerifyErrorMsgForNotEnteringFirstName()
	{
		return WithoutFirstNameErrormsg.getText();
	}
	public String VerifyErrorMsgForNotEnteringConfirmPassword()
	{
		return WithoutConfirmPasswordErrormsg.getText();
	}
	public String VerifyErrorMsgForNotEnteringPassword()
	{
		return WithoutPasswordErrormsg.getText();
	}
	
	public void RegisterCustomer(String firstname,String lastname,String email,String password,String confirmpassword)
	{
		FirstName.sendKeys(firstname);
		LastName.sendKeys(lastname);
		Email.sendKeys(email);
		Password.sendKeys(password);
		ConfirmPassword.sendKeys(confirmpassword);
		RegisterBtn.click();
	}
}


