package com.nizeapps.qnizer.exception;

import org.springframework.validation.BindingResult;

import com.nizeapps.qnizer.dom.Nizer;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 7732972006173265620L;
	private Nizer formObj;
	private BindingResult result;
	private String message;
	private boolean businessException;
	public ValidationException(String message, boolean businessException) {
		super(message);
		this.message = message;
		this.businessException = businessException;
	}
	public ValidationException(Nizer formObj, BindingResult result) {
		super();
		this.formObj = formObj;
		this.result = result;
	}
	public Nizer getFormObj() {
		return formObj;
	}
	public void setFormObj(Nizer formObj) {
		this.formObj = formObj;
	}
	public BindingResult getResult() {
		return result;
	}
	public void setResult(BindingResult result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isBusinessException() {
		return businessException;
	}
	public void setBusinessException(boolean businessException) {
		this.businessException = businessException;
	}
	

}
