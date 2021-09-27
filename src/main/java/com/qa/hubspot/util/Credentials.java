package com.qa.hubspot.util;

public class Credentials {
	
	String appUserName;
	String appPassword;
	
	public Credentials(String appUserName,String appPassword) {
		this.appUserName=appUserName;
		this.appPassword=appPassword;	
	}
	
	public String getAppUsername() {
		return	appUserName;
	}
	
	public void setAppUsername(String appUserName) {
		this.appUserName=appUserName;
		
	}
	
	public String getAppPassword() {
		return	appPassword;
	}
	
	public void setAppPassword(String appPassword) {
		this.appPassword=appPassword;
		
	}

}
