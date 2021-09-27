package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,AppConstants.DEFAULT_TIME_OUT);
		jsUtil=new JavaScriptUtil(driver);
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return true;
	}
	

	/**
	 * This method is used to get the title from the page
	 * @return
	 */
	
	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		}catch(Exception e) {
			System.out.println("Some exception got occured while getting the title...");
		}
		return null;
	}
	
	
	/**
	 * this method is used to create the webelement on the basis of by locator
	 * @param locator
	 * @return element
	 */
	public WebElement  getElement(By locator) {
		
		WebElement element=null;
		try {
			
			//if(waitForElementPresent(locator));
			element=driver.findElement(locator);
			if(highlighElement) {
				jsUtil.flash(element);
			}
			 
		}
		catch(Exception e) {
			System.out.println("Some exception got occured while creating the web element...");
		}
		return element;
	}
	
	/**
	 * this method is used to click the webelement on the basis of by locator
	 * @param locator
	 */
	public void doClick(By locator) {
	
		try {
			getElement(locator).click();	
		}catch(Exception e) {
			System.out.println("Some exception got occured while clicking on  the web element...");
		}
		
	}
	
	
	/**
	 * This method is used to send the value to the field
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator,String value) {
		try {
			WebElement element=getElement(locator);
			element.clear();
			element.sendKeys(value);
		}catch(Exception e) {
			System.out.println("Some exception got occured while entering value in a field...");
		}
	}
	
	/**
	 * This method is used to verify element displayed or not
	 * @param locator
	 * @return
	 */
	
	public boolean doisDisplyed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		}catch(Exception e) {
			System.out.println("Some exception got occured...");
		}
		return false;
	}
	
	/**
	 * This method is used get the text from webelement
	 * @param locator
	 * @return
	 */
	
	public String doGettext(By locator) {
		try {
			return getElement(locator).getText();
		}catch(Exception e) {
			System.out.println("Some exception got occured while getting the text from a webelement...");
		}
		return null;
		
	}
	
}
