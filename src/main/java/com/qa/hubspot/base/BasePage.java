package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	
	WebDriver driver;
	Properties prop;
	public static boolean highlighElement;
	public OptionsManager optionsManager;
	
	public WebDriver init_driver(String browserName) {
		highlighElement = prop.getProperty("highlight").equals("yes")? true : false;
		System.out.println("browser name is:" +browserName);	
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Public\\Pictures\\chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			//if(prop.getProperty("headless").equals("yes")) {
			//	ChromeOptions co=new ChromeOptions();
			//	co.addArguments("--headless");
			//	driver=new ChromeDriver(co);			
			//}
			//else {
			//	driver=new ChromeDriver();
			//}
		}
		else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			//if(prop.getProperty("headless").equals("yes")) {
			//	FirefoxOptions fo=new FirefoxOptions();
			//	fo.addArguments("--headless");
			//	driver=new FirefoxDriver(fo);			
			//}
			//else {
			//	driver=new FirefoxDriver();
			//}
			
		}else if(browserName.equals("safari")){
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver=new ChromeDriver();
		}
		
		else {
	 		
			System.out.println("browser Name" + browserName + "is not found , please pass the correct browser");
		}
		
		//driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
		//driver.get(url);
		
		return driver;
	}
	
	public Properties init_properties() {
		
		prop=new Properties();
		String path = null;
		String env=null;
		
		try {
			env=System.getProperty("env");
			//Pass the the value > mvn clean install -D in cmd prmpt
			if(env.equals("qa")) {
				 path="./src/main/java/com/qa/hubspot/config/qa.config.properties";
				
			}else if (env.equals("stg")){
				 path="./src/main/java/com/qa/hubspot/config/stg.config.properties";	
			}
		}catch(Exception e) {
			path="./src/main/java/com/qa/hubspot/config/config.properties";
		}
		
		try {
			FileInputStream ip=new FileInputStream(path);
			prop.load(ip);	
		}catch (FileNotFoundException e) {
			System.out.println("some issue with config properties... Please correct your config..");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
