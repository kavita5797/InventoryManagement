package com.humber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.ResponseVO;
import com.humber.service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/payment")
@Api(tags = "Payment API", description = "API for bill payment")
public class PaymentController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PaymentService paymentService;

	@PostMapping("/payBill")
	@ApiOperation(value = "Bill payment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Boolean> payBills(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "amount", required = true) double amount,
			@RequestParam(value = "paymentType", required = true) String paymentType) throws Exception {
		logger.info("Receiving Stock Bill payment: ", id, " amount:" + amount + " payment type:" + paymentType);
		boolean isPaid = paymentService.billPayment(id, amount, paymentType);
		if (isPaid) {
			return prepareSuccessResponse(isPaid);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}
}
