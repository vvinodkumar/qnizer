package com.nizeapps.qnizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.nizeapps.qnizer.dom.Counter;

@Service
public class CounterService {
  
	@Autowired 
	private MongoOperations mongo;
   
	public int getNextSequence(String collectionName) {
		Query query = new Query(Criteria.where("_id").is(collectionName));
		FindAndModifyOptions options = new FindAndModifyOptions();
 		Counter counter = mongo.findAndModify(query,new Update().inc("seq", 1),options.returnNew(true),Counter.class);
 		if(null == counter) {
 			counter = new Counter();
 			counter.setSeq(0);
 			counter.setId(collectionName);
 			mongo.save(counter);
 			counter = mongo.findAndModify(query,new Update().inc("seq", 1),options.returnNew(true),Counter.class);
 		}
		return counter.getSeq();
	}
}
