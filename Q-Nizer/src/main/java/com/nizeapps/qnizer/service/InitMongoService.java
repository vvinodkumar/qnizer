package com.nizeapps.qnizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class InitMongoService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void init() {
		
	}
}
