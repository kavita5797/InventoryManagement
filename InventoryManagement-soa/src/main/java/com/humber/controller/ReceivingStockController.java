package com.humber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.ResponseVO;
import com.humber.model.RecievingStock;
import com.humber.model.User;
import com.humber.service.ReceivingStockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/receivingStock")
@Api(tags = "Receiving Stock API", description = "API for Receiving Stock")
public class ReceivingStockController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReceivingStockService receivingStockService;
	
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
	
	@PostMapping("/payBill")
	@ApiOperation(value = "Receiving Stock - bill payment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Boolean> payBills(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "amount", required = true) double amount,
			@RequestParam(value = "paymentType", required = true) String paymentType) throws Exception {
		logger.info("Receiving Stock Bill payment: ", id , " amount:" + amount + " payment type:" + paymentType);
		boolean isPaid = receivingStockService.billPayment(id , amount , paymentType);
		if (isPaid) {
			return prepareSuccessResponse(isPaid);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}
	
}
