package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	BasePage basepage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest	
	public void setUp() {
		basepage=new BasePage();
		prop=basepage.init_properties();
		String browserName=prop.getProperty("browser");
		driver=basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage=new LoginPage(driver);
		userCred=new Credentials(prop.getProperty("username"),prop.getProperty("password")) ;
	}

	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		
		String title=loginPage.getPageTitle();
		System.out.println("Login page title is:"+title);
		Assert.assertEquals(title,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest(){
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	//Need to check one time
	@Test(priority=3)
	public void loginTest(){
		HomePage homePage=loginPage.doLogin(userCred);
		String title=homePage.getHomePageTitle();
		Assert.assertEquals(title,AppConstants.HOME_PAGE_TITLE);
		
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][]={ {"test11111@gmail.com","test123"},
						  {"test2@gmail.com"," "},
						  {"","test12345"},
						  {"test","test"},
						  {"",""}
						 };
		
		return data;
		
	}
	
	@Test(priority=4, dataProvider = "getLoginInvalidData", enabled=false)
	public void login_InvalidTetsCases(String username,String pwd){
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		  loginPage.doLogin(userCred);
		  Assert.assertTrue(loginPage.checkLoginErrorMsg());
		
	}
	 
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}	
}
