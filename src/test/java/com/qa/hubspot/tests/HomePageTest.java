package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {

	BasePage basepage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;

	@BeforeTest	
	public void setUp() throws InterruptedException {
		basepage=new BasePage();
		prop=basepage.init_properties();
		String browserName=prop.getProperty("browser");
		driver=basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage=new LoginPage(driver);
		userCred=new Credentials(prop.getProperty("username"),prop.getProperty("password")) ;
		homePage=loginPage.doLogin(userCred);
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle() {
		String title =homePage.getHomePageTitle();
		System.out.println("Home page title is:" + title);
		Assert.assertEquals(title,AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		String header =homePage.getHomePageHeader();
		System.out.println("Home page header is:" + header);
		Assert.assertEquals(header,AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3,enabled=false)
	public void verifyLoggedInUserTest() {
		String accountName =homePage.getLoggedInUserAccountName();
		System.out.println("Logged in Account Name:" + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}	
	
}
