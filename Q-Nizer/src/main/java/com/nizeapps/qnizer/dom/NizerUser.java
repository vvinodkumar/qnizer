package com.nizeapps.qnizer.dom;

import java.util.Calendar;

public class NizerUser {
	
	private String userName;
	private String password;
	private Calendar lastLoggedInDateTime;
	private String csrfToken;
	private Business business;
	private boolean agreedToTermsAndConditions;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Calendar getLastLoggedInDateTime() {
		return lastLoggedInDateTime;
	}
	public void setLastLoggedInDateTime(Calendar lastLoggedInDateTime) {
		this.lastLoggedInDateTime = lastLoggedInDateTime;
	}
	public String getCsrfToken() {
		return csrfToken;
	}
	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAgreedToTermsAndConditions() {
		return agreedToTermsAndConditions;
	}
	public void setAgreedToTermsAndConditions(boolean agreedToTermsAndConditions) {
		this.agreedToTermsAndConditions = agreedToTermsAndConditions;
	}
}
