package org.wecancodeit.shopbrowser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaintenanceControllerBrowseTest {

	// injectmocks creates an instance of the class
	// and injects the mocks that are created
	// inject mocks is for testing classes
	// mock is for object testing
	// mockbean is for testing spring setup

	@InjectMocks
	private MaintenanceController underTest;

	@Mock
	private CrudRepository<Product, Long> productRepo;

	@Mock
	private Product incoming;

	@Mock
	private Product upToDateProduct;

	@Test
	public void saveNewInstanceOfProduct() {
		MockitoAnnotations.initMocks(this);
		when(productRepo.save(incoming)).thenReturn(upToDateProduct);
		Product result = underTest.createProduct(incoming);
		assertThat(result, is(upToDateProduct));
	}

}
