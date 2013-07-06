package com.nizeapps.qnizer.dom;

import java.util.Calendar;
import java.util.List;

public class ResponseWrapper {
	
	private Nizer responseObject;
	@SuppressWarnings("rawtypes")
	private List responseCollection;
	private String responseCode;
	private MessageType messageType;
	private String txnRefNo;
	private Calendar responseDateTime; 
	private Error rootError;
	private List<Error> errors;
	private NizerUser user;
	
	public Nizer getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Nizer responseObject) {
		this.responseObject = responseObject;
	}
	public List getResponseCollection() {
		return responseCollection;
	}
	public void setResponseCollection(List responseCollection) {
		this.responseCollection = responseCollection;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getTxnRefNo() {
		return txnRefNo;
	}
	public void setTxnRefNo(String txnRefNo) {
		this.txnRefNo = txnRefNo;
	}
	public Calendar getResponseDateTime() {
		return responseDateTime;
	}
	public void setResponseDateTime(Calendar responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	public Error getRootError() {
		return rootError;
	}
	public void setRootError(Error rootError) {
		this.rootError = rootError;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	public NizerUser getUser() {
		return user;
	}
	public void setUser(NizerUser user) {
		this.user = user;
	}
}
