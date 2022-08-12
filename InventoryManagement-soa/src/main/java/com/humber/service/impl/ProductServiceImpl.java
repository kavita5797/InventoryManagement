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

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(String productId) {
		return productRepository.findById(productId);
	}

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

	@Override
	public Product saveProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		return productRepository.save(product);
	}

	@Override
	public boolean deleteProduct(String productId) {
		try {
			productRepository.deleteById(productId);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public long getTotalCount() {
		return productRepository.count();
	}

	@Override
	public long getTotalOutOfStockCount() {
		return productRepository.countByProductquality(0);
	}

}
