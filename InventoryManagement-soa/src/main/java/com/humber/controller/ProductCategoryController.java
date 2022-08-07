package com.humber.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.ResponseVO;
import com.humber.model.Product;
import com.humber.model.ProductCategory;
import com.humber.service.ProductCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("category")
@Api(tags = "Product Category API", description = "API for product category")
public class ProductCategoryController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@GetMapping
	@ApiOperation(value = "Get all products category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<List<ProductCategory>> getAllProductCategory() throws Exception {
		logger.info("REST request to get all products: {}");

		List<ProductCategory> productCategory = productCategoryService.getAllProductCategory();
		if (productCategory != null && !productCategory.isEmpty()) {
			return prepareSuccessResponse(productCategory);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}
	
	
	@PostMapping(value="createCategory")
	@ApiOperation(value="Create a product category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<ProductCategory> createCategory(@RequestBody ProductCategory product) throws Exception {
		logger.info("Create new product category:",product.toString());
		ProductCategory newProduct = productCategoryService.saveProductCategory(product);
		if(newProduct != null) {
			return prepareSuccessResponse(newProduct);
			
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
		
	}
	
	@DeleteMapping(value="deleteCategory")
	@ApiOperation(value="Delete Category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Boolean> deleteCategory(@RequestParam String productCategoryId) throws Exception {
		logger.info("Delete new Product Category: ",productCategoryId);
		boolean deleted = productCategoryService.deleteProductCategory(productCategoryId);
		if(deleted) {
			return prepareSuccessResponse(deleted);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_DELETED,
				CommonConstants.ErrorCodeMessage.NO_DATA_DELETED);
		
	}
	
	
	
	
	
}
