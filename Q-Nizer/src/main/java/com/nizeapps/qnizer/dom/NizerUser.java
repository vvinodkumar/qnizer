package com.nizeapps.qnizer.dom;

import java.util.Calendar;

public class NizerUser {
	
	private String userName;
	private Calendar lastLoggedInDateTime;
	private String csrfToken;
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

}
