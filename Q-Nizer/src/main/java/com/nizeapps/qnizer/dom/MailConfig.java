package com.nizeapps.qnizer.dom;

import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class MailConfig extends MandrillConfiguration implements Nizer {
	
	private String registrationFromEmailAddress;
	private String registrationFromName;
	private String registrationTemplate;
	private String registrationSubject;
	private String activationURL;
	private String unsubscribeURL;
	private String copyrightMessage;
	
	public String getRegistrationFromEmailAddress() {
		return registrationFromEmailAddress;
	}
	public void setRegistrationFromEmailAddress(String registrationFromEmailAddress) {
		this.registrationFromEmailAddress = registrationFromEmailAddress;
	}
	public String getRegistrationFromName() {
		return registrationFromName;
	}
	public void setRegistrationFromName(String registrationFromName) {
		this.registrationFromName = registrationFromName;
	}
	public String getRegistrationTemplate() {
		return registrationTemplate;
	}
	public void setRegistrationTemplate(String registrationTemplate) {
		this.registrationTemplate = registrationTemplate;
	}
	public String getActivationURL() {
		return activationURL;
	}
	public void setActivationURL(String activationURL) {
		this.activationURL = activationURL;
	}
	public String getUnsubscribeURL() {
		return unsubscribeURL;
	}
	public void setUnsubscribeURL(String unsubscribeURL) {
		this.unsubscribeURL = unsubscribeURL;
	}
	public String getCopyrightMessage() {
		return copyrightMessage;
	}
	public void setCopyrightMessage(String copyrightMessage) {
		this.copyrightMessage = copyrightMessage;
	}
	public String getRegistrationSubject() {
		return registrationSubject;
	}
	public void setRegistrationSubject(String registrationSubject) {
		this.registrationSubject = registrationSubject;
	}
	
	
	

}
