package com.humber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.service.ProductService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("product")
@Api(tags = "Product API", description = "API for products")
public class ProductController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	
}
