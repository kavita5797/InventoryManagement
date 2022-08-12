package com.humber.controller;

import java.util.List;

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
import com.humber.common.vo.DataTableVO;
import com.humber.common.vo.ResponseVO;
import com.humber.model.Merchant;
import com.humber.model.Merchant;
import com.humber.service.MerchantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("merchant")
@Api(tags = "Merchant API", description = "API for Merchants")
public class MerchantController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MerchantService merchantService;

	@GetMapping("getById/{id}")
	@ApiOperation(value = "Get merchant by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<Merchant> getMerchantById(@PathVariable("id") String id) throws Exception {
		logger.info("REST request to get merchant by id::" + id);

		Merchant merchant = merchantService.getMerchantById(id);
		if (merchant != null) {
			return prepareSuccessResponse(merchant);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@GetMapping
	@ApiOperation(value = "Get all merchants by search and pagination")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<DataTableVO<Merchant>> getAllMerchantsByFilter(
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size,
			@RequestParam(value = "sortField", required = false, defaultValue = "email") String sortField,
			@RequestParam(value = "sortOrder", required = false, defaultValue = "1") int sortOrder) throws Exception {

		DataTableVO<Merchant> data = merchantService.getAllMerchantsByFilter(searchText, offset, size, sortField,
				sortOrder);
		if (data != null) {
			return prepareSuccessResponse(data);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@GetMapping("getAllMerchants")
	@ApiOperation(value = "Get all merchants")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<List<Merchant>> getAllMerchants() throws Exception {

		List<Merchant> data = merchantService.getAllMerchants();
		if (data != null) {
			return prepareSuccessResponse(data);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@PostMapping(value = "addMerchant")
	@ApiOperation(value = "Create merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Merchant> create(@RequestBody Merchant merchant) throws Exception {
		logger.info("CREATE new merchant : ", merchant.toString());

		if (merchantService.getMerchantByEmailId(merchant.getEmail()) != null) {
			return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					CommonConstants.ErrorCode.USER_ALREADY_EXIST, CommonConstants.ErrorCodeMessage.USER_ALREADY_EXIST);
		}

		Merchant newMerchant = merchantService.saveMerchant(merchant);

		if (newMerchant != null) {
			return prepareSuccessResponse(newMerchant);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}

	@PutMapping(value = "updateMerchant")
	@ApiOperation(value = "Update merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5003-NO_DATA_UPDATED", response = ResponseVO.class) })
	public ResponseVO<Merchant> update(@RequestBody Merchant merchant) throws Exception {
		logger.info("Update new merchant : ", merchant.toString());
		if (merchantService.getMerchantById(merchant.getId()) == null) {
			return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					CommonConstants.ErrorCode.USER_NOT_FOUND, CommonConstants.ErrorCodeMessage.NO_DATA_UPDATED);
		}
		Merchant newMerchant = merchantService.updateMerchant(merchant);
		if (newMerchant != null) {
			return prepareSuccessResponse(newMerchant);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_UPDATED,
				CommonConstants.ErrorCodeMessage.NO_DATA_UPDATED);
	}

	@DeleteMapping(value = "deleteMerchant")
	@ApiOperation(value = "Delete merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5004-NO_DATA_DELETED", response = ResponseVO.class) })
	public ResponseVO<Boolean> delete(@RequestParam String merchantId) throws Exception {
		logger.info("Delete new merchant : ", merchantId);
		boolean deleted = merchantService.deleteMerchant(merchantId);
		if (deleted) {
			return prepareSuccessResponse(deleted);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_DELETED,
				CommonConstants.ErrorCodeMessage.NO_DATA_DELETED);
	}

	@GetMapping("count")
	@ApiOperation(value = "Get total merchat count")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class) })
	public ResponseVO<Long> getTotalCount() throws Exception {
		logger.info("REST request to get total merchant count::");
		long merchant = merchantService.getTotalCount();
		return prepareSuccessResponse(merchant);

	}
}
