package com.nizeapps.qnizer.dom;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nizeapps.qnizer.repository.CascadeSave;

@Document(collection="businesses")
public class Business  extends NizerHeader implements Nizer {

	@Id
	private ObjectId id;

	@NotEmpty
	@Length(min=3,max=50)
	private String name;
	private Pricing pricingModel;
	@NotNull
	@DBRef
	@CascadeSave
	private Address businessAddress;
	private String timeZone ="Singapore";
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
}
