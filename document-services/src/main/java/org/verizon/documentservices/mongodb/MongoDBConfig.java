package org.verizon.documentservices.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

/**
 * Spring MongoDB configuration file
 * 
 * @author Shankar K
 */
@Configuration
public class MongoDBConfig {

	/**
	 * @return mongoTemplate the mongoTemplate to access Mongo DB
	 * @throws Exception
	 */
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = 
		    new MongoTemplate(new MongoClient("127.0.0.1"),"yourdb");
		return mongoTemplate;
		
	}		
}

