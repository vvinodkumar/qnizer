package com.nizeapps.qnizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nizeapps.qnizer.dom.Customer;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

    
    private static List<Customer> custList = new ArrayList<Customer>();
    private static int tokenId = 0;
    
	@Override
	public List<Customer> getAllCustomers() {
		return custList;
	}

	@Override
	public Customer getCustomerByTokenId(int tokenId) {
		for (Customer cust : custList) {
            if (cust.getToken() == tokenId) {
                return cust;
            }
        }
        return null;
	}

	@Override
	public void addCustomer(Customer customer) {
		customer.setToken(++ tokenId);
		custList.add(customer);		
	}

	@Override
	public void deleteCustomerByTokenId(int tokenId) {
		 Customer cust = getCustomerByTokenId(tokenId);
	        if (cust != null) {
	        	custList.remove(cust);
	            tokenId--;
	        }
	}

	@Override
	public void deleteAll() {
		 custList.clear();
	     tokenId = 0;
	}

	@Override
	public void updateCustomer(Customer customer) {
		 Customer cust = getCustomerByTokenId(customer.getToken());
	        if (cust != null) {
	            custList.remove(cust);
	            custList.add(customer);
	        }
	}

}
