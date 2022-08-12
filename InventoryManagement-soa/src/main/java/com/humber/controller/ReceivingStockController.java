package com.humber.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.ResponseVO;
import com.humber.model.RecievingStock;
import com.humber.service.ReceivingStockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/receivingStock")
@Api(tags = "Receiving Stock API", description = "API for Receiving Stock")
public class ReceivingStockController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReceivingStockService receivingStockService;

	@GetMapping
	@ApiOperation(value = "GET all Receiving Stock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<List<RecievingStock>> getAllStockDetails() throws Exception {
		logger.info("GET all Receiving Stock : ");
		List<RecievingStock> receivedStockDetails = receivingStockService.getAllStockDetails();
		if (receivedStockDetails != null) {
			return prepareSuccessResponse(receivedStockDetails);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);
	}

	@PostMapping
	@ApiOperation(value = "Receiving Stock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<RecievingStock> create(@RequestBody RecievingStock receivedStock) throws Exception {
		logger.info("ADD Receiving Stock : ", receivedStock.toString());
		RecievingStock received = receivingStockService.save(receivedStock);
		if (received != null) {
			return prepareSuccessResponse(received);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}

}
