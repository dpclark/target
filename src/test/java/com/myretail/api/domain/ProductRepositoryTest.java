package com.myretail.api.domain;
import java.util.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.myretail.api.AbstractTest;

public class ProductRepositoryTest extends AbstractTest {
        @Autowired
        ProductRepository repository;

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
                List<Product> allProducts = repository.findAll();
                Assert.assertNotNull("expected to get a list of products",allProducts);
                Assert.assertTrue("expected the size of the list to be > 0",allProducts.size()>0);
        }
        @Test
        public void testFindByProductId() {
		Long id = Long.valueOf(5555);
                Assert.assertNotNull("expected to find cateogry toy",repository.findByProductId(id));
        }
        @Test
        public void testFindByCategory() {
                String category = "foo";
                String validCategory = "toy";
                List<Product> list = repository.findByCategory(category);
                Assert.assertTrue("should be empty on search for foo",list.size() == 0);
		list = repository.findByCategory(validCategory);
                Assert.assertTrue("should have products in the list",list.size() > 0);
                
        }

	@Test
	public void testSaveProduct() {
		Long id = Long.valueOf(12345);
		Product p = new Product();
		p.setProductId(id);
		repository.save(p);
		Assert.assertNotNull("should find the product by id",repository.findByProductId(id));
	}

	@Test
	public void testDeleteProduct() {
		Long id = Long.valueOf(12345);
		Product p = repository.findByProductId(id);
		repository.delete(p);
		Assert.assertNull("shouldn't find a product",repository.findByProductId(id));
	}
}
        
