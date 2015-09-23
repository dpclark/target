package com.myretail.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.myretail.api.domain.*; 

 /**
  * @author      david clark
  * @version     1.0
  * @since       2015-9-21
  * the implementation class that accesses the defined repository
  * to access the product catalog
  * @see ProductService
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository repository;
 
	/**
	 * constructor used by the spring runtime to inject the runtime value for the repository
	 * @param ProductRepositry class or interface that defines the methods to access the data store
	 * @return ProductServiceImpl
	*/
    	@Autowired
    	ProductServiceImpl(ProductRepository repository) {
        	this.repository = repository;
    	}
	
	/**
	 * given a category find all of the products in the repository that match
	 * @param category - the name of the category to filter with
	 * @return List<Product>
	*/
    	public List<Product> findByCategoryName(String category) {
    		List<Product> productsByCategory = new ArrayList<Product>();
		productsByCategory = repository.findByCategory(category);
		return productsByCategory;
    	}
	
	/**
	 * given an external id field find the product in the repository that matches it
	 * @param productId - the external id field
	 * @return Product
	*/
    	public Product findByProductId(Long productId) {
		Product product = new Product();
		product = repository.findByProductId(productId);
		return product;
    	}

	/**
	 * return all matching entities in the repository
	 * @return List<Product>
	*/
    	public List<Product> findAll() {
		List<Product> allProducts = new ArrayList<Product>();
		allProducts = repository.findAll();
		return allProducts;
    }
}
 
