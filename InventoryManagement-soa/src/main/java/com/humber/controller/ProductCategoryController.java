package com.humber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.service.ProductCategoryService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("category")
@Api(tags = "Product Category API", description = "API for product category")
public class ProductCategoryController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductCategoryService productCategoryService;
}
