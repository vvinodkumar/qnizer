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
import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.exception.ValidationException;
import com.nizeapps.qnizer.service.CounterService;
import com.nizeapps.qnizer.util.DateUtility;

@Repository
@Transactional	
public class BusinessRepository  {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
    public NizerUser findBusinessUserByUserName(String userName) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("userName").is(userName));
     	return mongoTemplate.findOne(query,NizerUser.class);
    }

    public void registerBusinessUser(NizerUser user)  {
    	
    	
    	 if (!mongoTemplate.collectionExists(NizerUser.class)) {
             mongoTemplate.createCollection(NizerUser.class);
         } 
         mongoTemplate.insert(user);
    }

}
