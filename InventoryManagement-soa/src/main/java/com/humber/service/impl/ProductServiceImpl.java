package com.humber.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.model.Product;
import com.humber.repository.ProductRepository;
import com.humber.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	
	/**
	 * This repository is used for the product operation.
	 */
	@Autowired
	ProductRepository productRepository;

	/**
	 * This method is used to get all the products.
	 *@return product
	 */
	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	/**
	 * This method is used to get product by id.
	 * @param productId
	 *@return product
	 */
	@Override
	public Optional<Product> getProductById(String productId) {
		return productRepository.findById(productId);
	}

	/**
	 * This method is used to update product.
	 * @param product
	 *@return product
	 *
	 */
	@Override
	public Product updateProduct(Product product) {
		if (productRepository.findById(product.getId()).isPresent()) {
			Product p = productRepository.findById(product.getId()).get();
			p.setProductname(product.getProductname());
			p.setProductcategory(product.getProductcategory());
			p.setProductdescription(product.getProductdescription());
			p.setProductlabel(product.getProductlabel());
			p.setProductprice(product.getProductprice());
			return productRepository.save(p);
		} else {
			return null;
		}
	}

	/**
	 *This method is used to save a product.
	 *@param product
	 *@return product
	 */
	@Override
	public Product saveProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		return productRepository.save(product);
	}

	/**
	 * This method is used to delete a product.
	 *@param productId
	 *@return isDeleted
	 */
	@Override
	public boolean deleteProduct(String productId) {
		try {
			productRepository.deleteById(productId);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 *This method is used to get total count.
	 *@return count
	 */
	@Override
	public long getTotalCount() {
		return productRepository.count();
	}

	/**
	 *This is used to get total count of out of stock products.
	 *@return count
	 */
	@Override
	public long getTotalOutOfStockCount() {
		return productRepository.countByProductquality(0);
	}

}
