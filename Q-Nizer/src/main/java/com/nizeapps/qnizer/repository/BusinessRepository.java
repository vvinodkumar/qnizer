package com.nizeapps.qnizer.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nizeapps.qnizer.dom.Customer;
import com.nizeapps.qnizer.service.CounterService;
import com.nizeapps.qnizer.util.DateUtility;

@Repository
@Transactional	
public class BusinessRepository  {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CounterService counter;
	
	public static final String CUSTOMER_COLLECTION_NAME = "customers";
	
	public static final String CUSTOMER_COLLECTION_SEQ = "customerseq";
	
	public List<Customer> findAllCustomers() {
		return mongoTemplate.findAll(Customer.class, CUSTOMER_COLLECTION_NAME);
	}

    public Customer findCustomerByTokenId(int tokenId) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("token").is(tokenId));
     	return mongoTemplate.findOne(query,Customer.class,CUSTOMER_COLLECTION_NAME);
    }

    public void addCustomer(Customer customer) {
    	
    	 if (!mongoTemplate.collectionExists(Customer.class)) {
             mongoTemplate.createCollection(Customer.class);
         } 
    	 customer.setToken(counter.getNextSequence(CUSTOMER_COLLECTION_SEQ));
    	 customer.setId(UUID.randomUUID().toString());
    	 customer.setCustomerFirstContactTime(DateUtility.getBusinessDateTime());
         mongoTemplate.insert(customer, CUSTOMER_COLLECTION_NAME);
    }

    public void updateCustomer(Customer customer) {
        mongoTemplate.save(customer,CUSTOMER_COLLECTION_NAME);
   }
    
    public void deleteCustomerByTokenId(int tokenId) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("token").is(tokenId));
        mongoTemplate.remove(query,Customer.class);
    }
	
}
