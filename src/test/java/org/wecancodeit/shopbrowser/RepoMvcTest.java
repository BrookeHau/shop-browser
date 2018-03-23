package org.wecancodeit.shopbrowser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
// adding controller class will only initialize that controller
// to make it smaller in scope
@WebMvcTest(ShoppingBrowseController.class)
public class RepoMvcTest {

	@Resource
	private MockMvc mvc;

	// MockMvc is a class utilized in application test
	// it creates a fake mvc to use for testing purposes

	// mockbean is used when testing spring; use if you're
	// ever testing the set up of the application, not just
	// variables, etc.
	// mock is used for unit testing
	@MockBean
	private CrudRepository<Product, Long> productRepo;

	@Test
	public void makeSureProductsPageWorks() throws Exception {
		mvc.perform(get("/products")).andExpect(status().isOk());
	}
	
	@Test
	public void findProductPageMakeSureLoads() throws Exception {
		//use when then return because no information 
		//is in the database
		long id = 42L;
		when(productRepo.findOne(id)).thenReturn(new Product("product"));
		mvc.perform(get("/products/42")).andExpect(status().isOk());
	}
	
	//when someone searches a product that doesn't exist we
	//want it to show a 404 error
	@Test 
	public void shouldNotFindProduct() throws Exception {
		mvc.perform(get("/products/2")).andExpect(status().isNotFound());
	}

}
