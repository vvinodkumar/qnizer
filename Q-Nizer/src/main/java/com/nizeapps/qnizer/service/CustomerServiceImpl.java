package com.nizeapps.qnizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nizeapps.qnizer.dom.Customer;
import com.nizeapps.qnizer.repository.CustomerRepository;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private SendTextMessageService textMsgService;
	
	@Autowired
	private CounterService counter;
    
	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAllCustomers();
	}

	@Override
	public Customer getCustomerByTokenId(int tokenId) {
		return custRepo.findCustomerByTokenId(tokenId);
	}

	@Override
	public void addCustomer(Customer customer) {
		custRepo.addCustomer(customer);	
		textMsgService.sendTextNotification(customer, true);
	}

	@Override
	public void deleteCustomerByTokenId(int tokenId) {
		custRepo.deleteCustomerByTokenId(tokenId);
	}

	

	@Override
	public void updateCustomer(Customer customer) {
		custRepo.updateCustomer(customer);	
	}

	@Override
	public void notifyCustomer(Customer customer) {
		textMsgService.sendTextNotification(customer, false);
		customer.setStatus("Notified");
		custRepo.updateCustomer(customer);	
	}

	

}
