package com.nizeapps.qnizer.dom;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nizeapps.qnizer.util.DateUtility;


public class Customer implements Nizer {

	private String id;
	@NotEmpty
	@Size(min=2, max=30)
	private String name;
	@Size(min=6,max=10)
	private String mobile;
	@Range(min=1,max=999)
	private int guestCount;
	private int suggestedWaitTime = 0; //Default to zero if no time is entered.
	@Transient
	private long actualWaitTime;
	private int token;
	private String specialRequest;
	private Calendar customerFirstContactTime; 
	private String status;
	private int serviceInTime=5;
	private int serviceRefNo=0;
	
	
	public int getServiceInTime() {
		return serviceInTime;
	}
	public void setServiceInTime(int serviceInTime) {
		this.serviceInTime = serviceInTime;
	}
	public int getServiceRefNo() {
		return serviceRefNo;
	}
	public void setServiceRefNo(int serviceRefNo) {
		this.serviceRefNo = serviceRefNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public void setCustomerFirstContactTime(Calendar customerFirstContactTime) {
		this.customerFirstContactTime = customerFirstContactTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setActualWaitTime(long actualWaitTime) {
		this.actualWaitTime = actualWaitTime;
	}

}
