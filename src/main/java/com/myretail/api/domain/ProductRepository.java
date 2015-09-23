package com.myretail.api.domain;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;

 /**
  * @author      david clark
  * @version     1.0
  * @since       2015-9-21
  * interface that extends the MongoRepository to add two specific query methods
  * in addition to the find* and other CRUD methods provided by the superclass
  * @see org.springframework.data.mongodb.repository.MongoRepository
 */
public interface ProductRepository extends MongoRepository<Product, String> {
	/**
	 * return a list of products filter by the category
	 * @param category - the name of the category to filter the product list with
	 * @return List<Product>
	 * @see ProductService#findByCategory
	*/
    	List<Product> findByCategory(String category);
	/**
	* return a specified instance of product using the extern id field
	* @param productId - the external id field of the product
	* @return Product
	* @see ProductService#findByProductId
	*/
    	Product findByProductId(Long productId);
}
