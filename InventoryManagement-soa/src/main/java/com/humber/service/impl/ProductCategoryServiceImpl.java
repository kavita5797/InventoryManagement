package com.humber.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.model.ProductCategory;
import com.humber.repository.ProductCategoryRepository;
import com.humber.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository pcatRepository;
	
	@Override
	public List<ProductCategory> getAllProductCategory() {
		return (List<ProductCategory>) pcatRepository.findAll();
	}

	@Override
	public ProductCategory saveProductCategory(ProductCategory product) {
		product.setId(UUID.randomUUID().toString());
		return pcatRepository.save(product);
	}

	@Override
	public boolean deleteProductCategory(String pcategoryid) {
		 try {
			 pcatRepository.deleteById(pcategoryid);
			 return true;
		 }
		 catch(Exception e) {
			 return false;
		 }
	}

	@Override
	public long getTotalCount() {
		return pcatRepository.count();
	}

}
