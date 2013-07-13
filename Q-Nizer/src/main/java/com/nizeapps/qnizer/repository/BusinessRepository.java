package com.nizeapps.qnizer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nizeapps.qnizer.dom.Address;
import com.nizeapps.qnizer.dom.Business;
import com.nizeapps.qnizer.dom.NizerUser;

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
    
    public void activateUser(NizerUser user)  {
        mongoTemplate.save(user);
   }
    
    public void removeBusinessUser(NizerUser user)  {

        mongoTemplate.remove(user);
   }
    
    public void removeBusiness(Business business)  {

        mongoTemplate.remove(business);
   }
    
    public void removeBusinessAddress(Address address)  {

        mongoTemplate.remove(address);
   }

}
