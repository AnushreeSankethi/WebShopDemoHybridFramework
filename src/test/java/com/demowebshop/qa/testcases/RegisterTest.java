package com.demowebshop.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demowebshop.base.Base;
import com.demowebshop.qa.pageObjects.HomePage;
import com.demowebshop.qa.pageObjects.RegisterPage;
import com.demowebshop.qa.utils.Utility;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerpage;

	public RegisterTest() {
		super();
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void LaunchApplication() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		registerpage = homepage.clickOnRegisterLink();

	}

	@Test(priority = 1)
	public void RegisterCustomerWithValidDetails() {

		// registerpage.SelectGender();
		registerpage.RegisterCustomer(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utility.GenerateEmailWithTimeStamp(), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.VerifyRegistrationSuccessMsg());

	}

	@Test(priority = 2)
	public void RegisterCustomerWithInvalidEmail() {
		registerpage.RegisterCustomer(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				dataProp.getProperty("invalidEmail"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.VerifyErrorMsgForWrongEmail().contains(dataProp.getProperty("emailWarningMsg")),
				"Entered WrongEmail Address");

	}

	@Test(priority = 3)
	public void RegisterCustomerWithoutLastName() {
		registerpage.RegisterCustomer(dataProp.getProperty("firstName"), "", Utility.GenerateEmailWithTimeStamp(),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.VerifyErrorMsgForNotEnteringLastName()
				.contains(dataProp.getProperty("lastNameWarningMsg")), "Last Name is mandatory to register a customer");

	}

	@Test(priority = 4)
	public void RegisterCustomerWithoutFirstName() {

		registerpage.RegisterCustomer("", dataProp.getProperty("lastName"), Utility.GenerateEmailWithTimeStamp(),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		Assert.assertTrue(registerpage.VerifyErrorMsgForNotEnteringFirstName().contains(
				dataProp.getProperty("firstNameWarningMsg")), "First Name is mandatory to register a customer");

	}

	@Test(priority = 5)
	public void RegisterCustomerWithoutProvidingConfirmPassword() {
		registerpage.RegisterCustomer(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utility.GenerateEmailWithTimeStamp(), prop.getProperty("validPassword"), "");

		Assert.assertTrue(
				registerpage.VerifyErrorMsgForNotEnteringConfirmPassword()
						.contains(dataProp.getProperty("confirmPasswordWarningMsg")),
				"Confirming a password is essential  to register a customer");

	}

	@Test(priority = 6)
	public void RegisterCustomerWithoutProvidingPassword() {
		registerpage.RegisterCustomer(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utility.GenerateEmailWithTimeStamp(), "", prop.getProperty("validPassword"));

		Assert.assertTrue(
				registerpage.VerifyErrorMsgForNotEnteringPassword()
						.contains(dataProp.getProperty("passwordWarningMsg")),
				"Confirming a password is essential  to register a customer");

	}

}

//Valid Credentials : demorsa@gmail.com  || 123456    demorsa12@gmail.com  || 123456
