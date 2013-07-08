package com.nizeapps.qnizer.dom;

public class Business implements Nizer {

	private String id;
	private String name;
	private String emailId;
	private String contactPersonName;
	private String contactPersonNumber;
	private Pricing pricingModel;
	private Address businessAddress;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonNumber() {
		return contactPersonNumber;
	}
	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}

	public Pricing getPricingModel() {
		return pricingModel;
	}
	public void setPricingModel(Pricing pricingModel) {
		this.pricingModel = pricingModel;
	}
	public Address getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(Address businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
}
