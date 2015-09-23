package com.myretail.api.service;

import java.util.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.myretail.api.AbstractTest;
import com.myretail.api.domain.*;

public class ProductServiceTest extends AbstractTest {
	@Autowired
	ProductService service;

	@Before
	public void setUp() {
		//blank
	}

	@After
	public void tearDown() {
		//blank
	}

	@Test
	public void testFindAll() {
		List<Product> allProducts = service.findAll();
		Assert.assertNotNull("expected to get a list of products",allProducts);
		Assert.assertTrue("expected the size of the list to be > 0",allProducts.size() > 0);
	}

	@Test
	public void testFindByProductId() {
		Long id = Long.valueOf(12345);
		Product product = service.findByProductId(id);
		Assert.assertNull("didn't expect to find a value for id 12345",product);
		Long validId = Long.valueOf(5555);
		product = service.findByProductId(validId);
		Assert.assertNotNull("expected to find a product",product);
	}

	@Test
	public void testFindByCategory() {
		String category = "foo";
		String validCategory = "toy";
		List<Product> list = service.findByCategoryName(category);
		Assert.assertTrue("should be empty on search for foo",list.size() == 0);
		list = service.findByCategoryName(validCategory);
		Assert.assertNotNull("valid category",list);
		Assert.assertTrue("should have products in the list",list.size() > 0);
		
	}
}
	
