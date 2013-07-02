package com.nizeapps.qnizer.exception;

import org.springframework.validation.BindingResult;

import com.nizeapps.qnizer.dom.Nizer;

public class ValidationException extends Exception {
	
	private Nizer formObj;
	private BindingResult result;
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

}
