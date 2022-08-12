package com.humber.service;

import java.util.List;
import java.util.Optional;

import com.humber.model.Product;

public interface ProductService {

	/**
	 * This service is to get all products.
	 * 
	 * @return
	 */
	List<Product> getAllProducts();

	/**
	 * This service is to get product by id.
	 * 
	 * @param productId
	 * @return
	 */
	Optional<Product> getProductById(String productId);

	/**
	 * This service is to save product.
	 * 
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product);

	/**
	 * This service is to delete product.
	 * 
	 * @param productId
	 * @return
	 */
	boolean deleteProduct(String productId);

	/**
	 * This service is to update product.
	 * 
	 * @param product
	 * @return
	 */
	Product updateProduct(Product product);

	/**
	 * This service is to get all products count.
	 * 
	 * @return
	 */
	long getTotalCount();

	/**
	 * This service is to get all out of stock products.
	 * 
	 * @return
	 */
	long getTotalOutOfStockCount();

}
