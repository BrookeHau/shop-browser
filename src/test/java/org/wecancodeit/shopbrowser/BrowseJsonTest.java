package org.wecancodeit.shopbrowser;

import java.io.IOException;

import javax.annotation.Resource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class BrowseJsonTest {

	@Resource
	// the jacksontester converts the product DTO
	// (data transfer object) into json
	private JacksonTester<Product> productJson;

	// serialization converts object into a string
	// deserialization converts string into an object
	// convert object product to json (line 28)
	@Test
	public void shouldSerialize() throws IOException {
		Product product = new Product("product");
		JsonContent<Product> content = productJson.write(product);
		// extract the value of the given json(content)
		// @.name calls the name within the Product object
		// because it is getName in product
		Assertions.assertThat(content).extractingJsonPathValue("@.name").isEqualTo("product");
	}
	
	//deserialization, convert string to object
	@Test
	public void shouldDeserialize() throws IOException {
		//the line below is the json object {"name": "product name"}
		String jsonContent = "{\"name\" : \"product name\"}";
		//return object from parsed json string
		Product parsed = productJson.parseObject(jsonContent);
		//assertions states that since we converted to an object above
		//we can now use the method getName to call the product name
		Assertions.assertThat(parsed.getName()).isEqualTo("product name");
				
	}

}
