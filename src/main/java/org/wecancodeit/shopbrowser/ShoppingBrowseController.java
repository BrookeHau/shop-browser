package org.wecancodeit.shopbrowser;

import javax.annotation.Resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingBrowseController {

	@Resource
	private CrudRepository<Product, Long> productRepo;

	@RequestMapping("/products")
	public Iterable<Product> findProducts() {
		return productRepo.findAll();
	}

	@RequestMapping("/products/{id}")
	// pathvariable annotation indicates that a method parameter
	// should be bound to a URI template variable
	public Product findProduct(@PathVariable(name = "id") long id) {
		Product selectedProduct = productRepo.findOne(id);
		return selectedProduct;
	}
}
