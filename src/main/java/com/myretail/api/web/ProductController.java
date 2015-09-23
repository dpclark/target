package com.myretail.api.web;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myretail.api.service.*;
import com.myretail.api.domain.*; 

 /**
  * @author      david clark
  * @version     1.0
  * @since       2015-9-21
  * The class that maps request uris to service method calls
 */
@RestController
@RequestMapping("/api/products")
final class ProductController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    	private final ProductService service;
        
	/**
	 * constructor used by the spring runtime for dependency injection
	 * @param ProductService the class/interface configured either by the runtime or specified
         * that will be able to satisft the queries 
	 * @returns ProductController
	 * @see ProductService
	*/
    	@Autowired
    	public ProductController(ProductService service) {
    		this.service = service;
    	}
    
	/**
	 * method to get the root request /api/products
	 * @returns List<Product>
	 * @see ProductService#findAll
	 * @see Product
	*/
    	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    	public @ResponseBody List<Product> findAll() {
    		List<Product> allProducts = new ArrayList<Product>();
    		allProducts = service.findAll();
    		return allProducts;
    	}
    
	/**
	 * method to lookup a specific product by /api/product/{id}
	 * @param id - the external id of the product
	 * @returns Product
	 * @see ProductService.findByProductId
	 * @see Product
	*/
    	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    	public @ResponseBody Product findByProductId(@PathVariable("id") Long id) {
    		Product product = new Product();
    		product = service.findByProductId(id);
    		return product;
    	}
    
	/**
	 * method to lookup a list of products by category using /api/products/category?name=<foo>
	 * @param category (the uri request parameter is name) the name of the category that will be used
	 * to filter the list of products
	 * @returns List<Product>
	 * @see ProductService#findByCategoryName
	 * @see Product
	*/
    	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    	public @ResponseBody List<Product> findByCategoryName(@RequestParam("name") String category) {
    		List<Product> productsByCategory = new ArrayList<Product>();
    		productsByCategory = service.findByCategoryName(category);
    		return productsByCategory;
    }
    
}
