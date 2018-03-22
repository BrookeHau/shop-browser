package org.wecancodeit.shopbrowser;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

public class ShoppingBrowseControllerTest {

	@InjectMocks
	private ShoppingBrowseController underTest;

	@Mock
	private CrudRepository<Product, Long> productRepo;

	@Mock
	private Product product;

	@Before
	public void setUp() {
		// initmocks(this) initializes the fields annotated with mockito annotations
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetProducts() {
		Iterable<Product> result = underTest.findProducts();
		assertThat(result, is(nullValue()));
	}

	@Test
	public void shouldGetProductsFromDb() {
		when(productRepo.findAll()).thenReturn(Collections.singleton(product));
		Iterable<Product> result = underTest.findProducts();
		assertThat(result, contains(product));
	}
	
	@Test
	public void shouldGetIndividualProduct() {
		Long id = 2L;
		when(productRepo.findOne(id )).thenReturn(product);
		Product result = productRepo.findOne(id);
		assertThat(result, is(product));
	}
}
