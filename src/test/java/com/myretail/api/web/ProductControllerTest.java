package com.myretail.api.web;

import java.util.*;
import org.junit.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.myretail.api.AbstractTest;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
public class ProductControllerTest extends AbstractTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testFindAllProducts() throws Exception {
	 mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByCategory() throws Exception {
    	mockMvc.perform(get("/api/products?category=toy"))
		.andExpect(status().isOk());
   }

   @Test
   public void testFindByProductId() throws Exception {
	mockMvc.perform(get("/api/products/5555"))
		.andExpect(status().isOk());
   }	
}
