package com.demowebshop.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(linkText="Log in")
	private WebElement LoginLink;
	
	@FindBy(className="account")
	private WebElement AccountName;
	
	@FindBy(id="small-searchterms")
	private WebElement ProductName;
	
	@FindBy(xpath="//input[@value='Search']")
	private WebElement SearchBtn;
	
	@FindBy(linkText="Register")
	private WebElement RegisterLink;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public LoginPage clickOnLoginLink()
	{
		LoginLink.click();
		return new LoginPage(driver);
	}
	
	public boolean searchForLoggedInAccount()
	{
		boolean displayStatus=AccountName.isDisplayed();
		return displayStatus;
	}
	
	public void EnterProduct(String ProdName)
	{
		ProductName.sendKeys(ProdName);
	
		
		
	}
	public SearchPage ClickOnsearchIcon()
	{
		SearchBtn.click();
		return new SearchPage(driver);
		
	}
	
	
	public RegisterPage clickOnRegisterLink()
	{
		RegisterLink.click();
		return new RegisterPage(driver);
	}
}
