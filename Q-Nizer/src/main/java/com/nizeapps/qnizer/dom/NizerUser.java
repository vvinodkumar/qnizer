package com.nizeapps.qnizer.dom;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nizeapps.qnizer.repository.CascadeSave;

@Document(collection="users")
public class NizerUser extends NizerHeader implements Nizer  {
	
	@Id
	private ObjectId id;
	
	@Email
	private String userName;
	private String password;
	@Transient
	private String passwordConfirm;
	private Calendar lastLoggedInDateTime;
	private String csrfToken;
	@NotNull
	@DBRef
	@CascadeSave
	private Business business;
	private boolean agreedToTermsAndConditions;
	@NotEmpty
	@Length(max=30)
	private String firstName;
	@NotEmpty
	@Length(max=30)
	private String lastName;
	@NotEmpty
	@Length(min=6, max=10)
	private String phoneNo;
	
	private Calendar activationDateTime;
	
	public Calendar getActivationDateTime() {
		return activationDateTime;
	}
	public void setActivationDateTime(Calendar activationDateTime) {
		this.activationDateTime = activationDateTime;
	}
	private String activationKey;
	private UserStatus status; 
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getActivationKey() {
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	

}
