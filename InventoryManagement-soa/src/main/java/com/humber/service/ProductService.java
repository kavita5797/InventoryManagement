package com.humber.service;

import java.util.List;
import java.util.Optional;

import com.humber.model.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Optional<Product> getProductById(String productId);
	
	Product saveProduct(Product product);
	
	boolean deleteProduct(String productId);
	
	Product updateProduct(Product product);
	
	

}
