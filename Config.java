package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

@Configuration
public class Config {

	public @Bean MongoDbFactory mongoDbFactory() {
		MongoClientOptions build = new MongoClientOptions.Builder().build();
		ServerAddress addr = new ServerAddress("127.0.0.1", 27017);
		MongoClient mc = new MongoClient(addr, build);
		return new SimpleMongoDbFactory(mc, "test");
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}
	
}
