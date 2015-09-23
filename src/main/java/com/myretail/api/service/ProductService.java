package com.myretail.api.service;

import java.util.*;
import com.myretail.api.domain.Product;
/**
  * @author      david clark
  * @version     1.0
  * @since       2015-9-21
  * this interface is the list of methods that the controller will be able to call to 
 *  access the mongo repository
 */
public interface ProductService {
	/**
	 * findAll will return all products in a given collection
	 * @return List<Product>
	*/
	public List<Product> findAll();
	/**
	 * return all products for a given category
	 * @param catgory the text name of the category to filter with
	 * @return List<Product>
	*/
	public List<Product> findByCategoryName(String category);
	/**
	 * find a specific product by it&amp;s external id field
	 * @param Long productId - the external id field
	 * @return Product
	*/
	public Product findByProductId(Long productId);
}
