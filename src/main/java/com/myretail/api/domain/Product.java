package com.myretail.api.domain;

import java.util.Date;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
 /**
  * @author      david clark
  * @version     1.0
  * @since       2015-9-21
  * This is the entity class for this application
 */
@Document(collection="products")
public class Product {
	/**
	 * the MongoDB ObjectId field
	*/
	@Id
        private String id;
	/**
	 * the external system identifier
	 * annotated with indexed so that we can search on it
	*/
	@Indexed
	private Long productId;
        private String sku;
	        private Double price;
        private String description;
	/**
	 * the category of this product
	 * annotated with indexed so that we can search on it
	*/
	@Indexed
        private String category;
        private Date lastUpdateDate;
        
        public Product()
        {
        }

	/**
  	 * constructor used by runtime to populate the object from the repository
  	 * <p>
  	 * @param id the objectid used by mongo to uniquely identify documents
  	 * @param productId the external product id that may map into other systems
  	 * @param sku stock keeping unit 
  	 * @param price the price of the product
  	 * @param description the textual description of the product
  	 * @param category the category underwhich this product is categorized
  	 * @param lastUpdateDate the date when this product was last updated
  	 * @return Product
 	*/
        @PersistenceConstructor
        public Product(String id, Long productId, String sku, Double price, String description, String category, Date lastUpdateDate) {
                this.id = id;
		this.productId = productId;
                this.sku = sku;
                this.price = price;
                this.description = description;
                this.category = category;
                this.lastUpdateDate = lastUpdateDate;
        }
	
	/**
         * @return the id field which is a MongoDB objectid 
	*/
        public String getId() {
                return this.id;
        }

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the external product id for this entity
	*/
	public Long getProductId() {
		return this.productId;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the external stock keeping unit value for this entity
	*/
        public String getSku() {
                return this.sku;
        }
	
	public void setPrice(Double price) {
		this.price = price; //add assertions ie negative price etc.
	}

	/**
	 * @return the price as a Double 
	*/
        public Double getPrice() {
                return this.price;
        }

	public void setDescription(String description) {
		this.description = description;
	}
        
	/**
	 * @return a string containing the description of this entity
	*/
        public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return a string containing the category of this entity
	*/
	public String getCategory() {
		return this.category;
	}
	
	public void setLastUpdateDate(Date date) {
		this.lastUpdateDate = date;
	}
	/**
	 * @return the date that this entity was last updated
	*/
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;     
	}
}

