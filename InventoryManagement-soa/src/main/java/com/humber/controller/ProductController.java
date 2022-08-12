package com.humber.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseVO<List<Product>> getAllProducts() throws Exception {
		logger.info("REST request to get all products: {}");

		List<Product> products = productService.getAllProducts();
		if (products != null && !products.isEmpty()) {
			return prepareSuccessResponse(products);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}
	
	
	@GetMapping(value = "getProductById/{id}")
	@ApiOperation(value = "Get product by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<Optional<Product>> getProductById(@PathVariable("id") String id)
			throws Exception {
		logger.info("REST request to get a Product by id: {}", id);

		Optional<Product> product = productService.getProductById(id);
		if (product != null) {
			return prepareSuccessResponse(product);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}
	
	
	@PostMapping(value = "createProduct")
	@ApiOperation(value = "Create a new Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Product> createProduct(@RequestBody Product product) throws Exception {
		logger.info("CREATE new product : ", product.toString());
		Product newProduct = productService.saveProduct(product);
		if (newProduct != null) {
			return prepareSuccessResponse(newProduct);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}
	
	
	@PutMapping(value = "updateProduct")
	@ApiOperation(value = "Update a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5003-NO_DATA_UPDATED", response = ResponseVO.class) })
	public ResponseVO<Product> updateProduct(@RequestBody Product product) throws Exception {
		logger.info("Update new user : ", product.toString());
		Product newProduct = productService.updateProduct(product);
		if (newProduct != null) {
			return prepareSuccessResponse(newProduct);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_UPDATED,
				CommonConstants.ErrorCodeMessage.NO_DATA_UPDATED);
	}
	
	
	
	@DeleteMapping(value = "deleteProduct")
	@ApiOperation(value = "Delete a Product by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Boolean> deleteProduct(@RequestParam String productId) throws Exception {
		logger.info("Delete a product : ", productId);
		boolean deleted = productService.deleteProduct(productId);
		if (deleted) {
			return prepareSuccessResponse(deleted);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_DELETED,
				CommonConstants.ErrorCodeMessage.NO_DATA_DELETED);
	}
	
	@GetMapping("count")
	@ApiOperation(value = "Get total product count")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class) })
	public ResponseVO<Long> getTotalCount() throws Exception {
		logger.info("REST request to get total product count::");
		long productCount = productService.getTotalCount();
		return prepareSuccessResponse(productCount);

	}
	
	@GetMapping("outofstock")
	@ApiOperation(value = "Get total out of stock product count")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class) })
	public ResponseVO<Long> getTotalOutOfStockCount() throws Exception {
		logger.info("REST request to get total out of stock product count::");
		long productCount = productService.getTotalCount();
		return prepareSuccessResponse(productCount);

	}


}
