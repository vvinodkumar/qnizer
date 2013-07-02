package com.nizeapps.qnizer.service;

import java.util.List;

import com.nizeapps.qnizer.dom.Customer;

public interface CustomerService {
	
	   public List<Customer> getAllCustomers();

	    public Customer getCustomerByTokenId(int tokenId);

	    public void addCustomer(Customer customer);

	    public void deleteCustomerByTokenId(int tokenId);

	    public void deleteAll();

	    public void updateCustomer(Customer customer);

}
