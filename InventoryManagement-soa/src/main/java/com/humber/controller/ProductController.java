package com.humber.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.ResponseVO;
import com.humber.model.Product;
import com.humber.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("product")
@Api(tags = "Product API", description = "API for products")
public class ProductController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@GetMapping
	@ApiOperation(value = "Get all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<List<Product>> getAllUsers() throws Exception {
		logger.info("REST request to get all products: {}");

		List<Product> products = productService.getAllProducts();
		if (products != null && !products.isEmpty()) {
			return prepareSuccessResponse(products);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

}
