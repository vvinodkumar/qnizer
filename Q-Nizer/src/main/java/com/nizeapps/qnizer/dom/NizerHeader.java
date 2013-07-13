package com.nizeapps.qnizer.dom;

import java.util.Calendar;

public class NizerHeader {
	
	private Calendar createdDateTime = null;
	private Calendar modifiedDateTime = null;
	private String createdBy = null;
	private String modifiedBy = null;
	public Calendar getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Calendar createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Calendar getModifiedDateTime() {
		return modifiedDateTime;
	}
	public void setModifiedDateTime(Calendar modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
}
