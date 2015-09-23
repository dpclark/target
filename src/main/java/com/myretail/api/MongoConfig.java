package  com.myretail.api;

import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author      david clark
 * @version     1.0
 * @since       2015-09-21
 *  
 *  A class to contain the JavaConfig configuration
 *  that enables spring to setup the required Mongo runtime beans.
 *  @see org.springframework.data.mongodb.config.AbstractMongoConfiguration
 */

@Configuration
@EnableMongoRepositories("com.myretail.api.domain")
class MongoConfig extends AbstractMongoConfiguration {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * {@inheritDoc}
 	*/
  	@Override
  	protected String getDatabaseName() {
		//can use propertysource and read from a file for runtime config
    		return "products";
  	}

	/**
	 * create a mongoDbFactory using the overriden {@link #getDatabaseName} method and default values for server and port.
         * leverage spring to automatically create and instance for use in the application context as well.
	 * @throws Exception
	 * @see #getDatabaseName
	*/
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), getDatabaseName());
	}
	
	@Bean 
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	/**
	 * {@inheritDoc}
	*/
	@Bean
	@SuppressWarnings( "deprecation" )
	@Override
	public Mongo mongo() throws Exception {
		return new Mongo();
	}

}
