package com.humber.service;

import java.util.List;

import com.humber.model.ProductCategory;

public interface ProductCategoryService {

	/**
	 * @return productcategory
	 */
	List<ProductCategory> getAllProductCategory();
	
	/**
	 * @param productcategory
	 * @return productcategory
	 */
	ProductCategory saveProductCategory(ProductCategory product);

	/**
	 * @param pcategoryid
	 * @return isDeleted
	 */
	boolean deleteProductCategory(String pcategoryid);

	/**
	 * @return count
	 */
	long getTotalCount();


}
