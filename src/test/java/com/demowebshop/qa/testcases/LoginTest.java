package com.demowebshop.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demowebshop.base.Base;
import com.demowebshop.qa.pageObjects.HomePage;
import com.demowebshop.qa.pageObjects.LoginPage;
import com.demowebshop.qa.pageObjects.PasswordRecoveryPage;
import com.demowebshop.qa.utils.Utility;

@Test(priority = 1)
public class LoginTest extends Base {
	LoginPage loginpage;
	PasswordRecoveryPage passwordRecoveryPage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void LaunchApplication() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		loginpage = homepage.clickOnLoginLink();

	}

	@Test(priority = 1, dataProvider = "ValidCredentialsSupplier")
	public void LoginWithValidCredentials(String Email, String password) {
		loginpage.login(Email, password);
		HomePage homepage = new HomePage(driver);
		homepage.searchForLoggedInAccount();

	}

	@DataProvider(name = "ValidCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utility.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void LoginWithInValidEmailAndValidPassword() {
		loginpage.login(dataProp.getProperty("invalidEmailID"), prop.getProperty("validPassword"));

		Assert.assertEquals(loginpage.GetTextOfErrorMsgonInvalidField(),
				"Login was unsuccessful. Please correct the errors and try again.", "Login was unsuccessful");

	}

	@Test(priority = 3)
	public void LoginWithValidEmailAndInValidPassword() {
		loginpage.login(prop.getProperty("validEmailID"), dataProp.getProperty("invalidPassword"));

		Assert.assertEquals(loginpage.GetTextOfErrorMsgonInvalidField(),
				"Login was unsuccessful. Please correct the errors and try again.", "Login was unsuccessful");

	}

	@Test(priority = 4)
	public void LoginWitoutAnyDetails() {
		loginpage.ClickOnLoginLink();
        Assert.assertEquals(loginpage.GetTextOfErrorMsgonInvalidField(), dataProp.getProperty("loginUnsuccessfulMsg"),
				"Login was unsuccessful");

	}

	@Test(priority = 5)
	public void LoginByClickingOnForgotPasswordLink() {
		loginpage.login(prop.getProperty("validEmailID"), "1234");
        passwordRecoveryPage = loginpage.ClickOnForgotPwdLink();
		passwordRecoveryPage.RecoverPassword(prop.getProperty("validEmailID"));
		Assert.assertEquals(passwordRecoveryPage.checkSuccessMessage(),
				dataProp.getProperty("forgotPasswordSuccessMsg"), "Unable to send mail to reset password");

	}
	
	
	

}
