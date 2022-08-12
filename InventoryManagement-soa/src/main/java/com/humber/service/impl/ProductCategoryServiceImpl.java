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


	/**
	 * 
	 *This repository is used for the productCategory operation.
	 */
	@Autowired
	ProductCategoryRepository pcatRepository;
	
	
	/**
	 * 
	 *This method is used to get all product category.
	 *@return productcategory
	 */
	@Override
	public List<ProductCategory> getAllProductCategory() {
		return (List<ProductCategory>) pcatRepository.findAll();
	}

	
	/**
	 *This method is used to save a product category.
	 *@param productcategory
	 *@return productcategory
	 */
	@Override
	public ProductCategory saveProductCategory(ProductCategory product) {
		product.setId(UUID.randomUUID().toString());
		return pcatRepository.save(product);
	}

	/**
	 *This method is used to delete a product category
	 *@param productcategoryid
	 *@return isDeleted
	 */
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

	/**
	 *This method is used to get total count
	 *@return count
	 */
	@Override
	public long getTotalCount() {
		return pcatRepository.count();
	}

}
