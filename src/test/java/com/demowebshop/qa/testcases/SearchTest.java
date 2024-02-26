package com.demowebshop.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demowebshop.base.Base;
import com.demowebshop.qa.pageObjects.HomePage;
import com.demowebshop.qa.pageObjects.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;

	public SearchTest() {
		super();
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void LaunchApplication() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
	}

	@Test(priority = 1)
	public void SearchwithValidProductDetails() {
    homepage.EnterProduct(dataProp.getProperty("validProductName"));
    searchpage=homepage.ClickOnsearchIcon();
		
		Assert.assertTrue(searchpage.SearchForEnteredProduct());
	}

	@Test(priority = 2)
	public void SearchwithInValidProductDetails() {
		homepage.EnterProduct(dataProp.getProperty("invalidProductName"));
		searchpage=homepage.ClickOnsearchIcon();

		Assert.assertEquals(searchpage.GetErrorMessageForInvalidProduct(),
				dataProp.getProperty("messageForInvalidProductSearch"), "There were no matched product present");
	}

	//@Test(priority = 3,dependsOnMethods= {"SearchwithInValidProductDetails()"})
	@Test(priority = 3)
	public void SearchwithoutProvidingProductDetails() {

		homepage.ClickOnsearchIcon();
		driver.switchTo().alert().accept();
	}
}
