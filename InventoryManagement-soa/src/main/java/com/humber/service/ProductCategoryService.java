package com.humber.service;

import java.util.List;

import com.humber.model.ProductCategory;

public interface ProductCategoryService {

	List<ProductCategory> getAllProductCategory();
	
	ProductCategory saveProductCategory(ProductCategory product);

	boolean deleteProductCategory(String pcategoryid);


}
