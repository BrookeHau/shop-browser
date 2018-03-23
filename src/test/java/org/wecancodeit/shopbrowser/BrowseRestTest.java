package org.wecancodeit.shopbrowser;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

//testing rest controller
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BrowseRestTest {

	// testresttemplate is useful in integration tests
	// you can get a vanilla template or one that sends basic http
	// authentication
	@Resource
	private TestRestTemplate restTemplate;

	@Resource
	private ObjectMapper jsonMapper;

	@Test
	public void getValidHttpRequest() {
		// responseentity represents the entire Http response
		// you can control anything that goes into it: status code, headers, body
		ResponseEntity<String> response = restTemplate.getForEntity("/products", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}

	@Test
	public void createProduct() {
		Product product = new Product(" new product");
		ResponseEntity<Product> response = restTemplate.postForEntity("/products", product, Product.class);
		// prior test was to verify the webpage was accessible
		// this test is to ensure the body of the webpage is populated
		Product created = response.getBody();
		assertThat(created.getId(), is(greaterThan(0L)));
	}

}
