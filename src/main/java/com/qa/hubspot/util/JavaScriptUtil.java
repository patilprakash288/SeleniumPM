package com.qa.hubspot.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver){
		this.driver=driver;	
	}
	
	/*
	 * This function is used the get the title using Java script
	 * 
	 * 
	 */
	public  String  getTitleByJS() {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		String title=js.executeScript("return document.title;").toString();
		return title;		
	}
	
	public void  generateAlert(String message) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("alert('"+message+"')");
				
	}
	
	public  void  refreshBrowserByJS() {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("history.go(0)");		
	}
	
	public  String  getBrowserInfo() {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		String uAgent=js.executeScript("return navigator.userAgent").toString();
		return uAgent;		
	}
	
	public void  sendKeysUsingJSWithID(String id, String value) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");	
	}
	
	public  void  clickElementByJS(WebElement element) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();",element);
				
	}
	
	
	public void drawBorder(WebElement element) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border='3px solid red'",element);
		
	}
	
	public void flash(WebElement element) {
		
		//JavascriptExecutor js=((JavascriptExecutor)driver);
		String bgcolor=element.getCssValue("backgroundColor");
		for(int i=0;i<20;i++) {
			changeColor("rgb(0,200,0)",element);//1
			changeColor(bgcolor,element);//2
		}
	}

	private void changeColor(String color, WebElement element) {
	
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
		try {
			Thread.sleep(20);
			
		}catch(InterruptedException E) {
			
		}
		
	}

	public String getPageInnertext() {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		String pageText=js.executeScript("return document.documentElement.innertext").toString();
		return pageText;
	}
	
	/*
	 * This function is used to the Scroll down the Page using the Java script
	 */
	
	public void  scrollPageDown() {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
	}
	
	/*
	 * This function is used to Scoll down and go to the perticular object
	 */
	
	public void  scrollIntoView(WebElement element) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		
	}
	
	
}
