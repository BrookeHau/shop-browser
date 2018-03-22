package org.wecancodeit.shopbrowser;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class BrowseJsonTest {

	@Resource
	private JacksonTester<Product> productJson;
	

}
