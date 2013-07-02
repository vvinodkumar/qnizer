package com.nizeapps.qnizer.dom;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.nizeapps.qnizer.util.DateUtility;

public class Customer implements Nizer {

	@NotEmpty
	@Size(min=2, max=30)
	private String name;
	@Size(min=6,max=10)
	private String mobile;
	@Range(min=1,max=999)
	private int guestCount;
	private int suggestedWaitTime = 0; //Default to zero if no time is entered.
	private long actualWaitTime;
	private int token;
	private String specialRequest;
	private Calendar customerFirstContactTime; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getSuggestedWaitTime() {
		return suggestedWaitTime;
	}
	public void setSuggestedWaitTime(int suggestedWaitTime) {
		this.suggestedWaitTime = suggestedWaitTime;
	}
	
	public long getActualWaitTime() {
		return DateUtility.getElapsedTimeInMinutes(getCustomerFirstContactTime());
	}

	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	
	public int getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	
	public Calendar getCustomerFirstContactTime() {
		return customerFirstContactTime;
	}
	public void setCustomerFirstContactTime() {
		this.customerFirstContactTime = DateUtility.getBusinessDateTime();
	}
}
