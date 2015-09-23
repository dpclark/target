package com.myretail.api;

import org.springframework.context.annotation.Import;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author      david clark
 * @version     1.0      
 * @since       2015-09-21
 */
@SpringBootApplication
@Import(MongoConfig.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
