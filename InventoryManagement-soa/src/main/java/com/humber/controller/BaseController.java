package com.humber.controller;

import org.springframework.http.HttpStatus;

import com.humber.common.vo.ResponseVO;

public class BaseController {
	/**
	 * This method is used to prepare a success response.
	 * @param <T>
	 * @param data
	 * @return response
	 */
	protected <T> ResponseVO<T> prepareSuccessResponse(T data) {

		ResponseVO<T> response = new ResponseVO<>();

		response.setData(data);
		response.setStatusCode(HttpStatus.OK.value());
		response.setErrorCode(null);
		response.setMessage(HttpStatus.OK.name());
		response.setStatus(true);
		return response;
	}

	/**
	 * This method is used to prepare an error response.
	 * @param <T>
	 * @param statusCode
	 * @param errorCode
	 * @param errorMessage
	 * @return Response
	 */
	protected <T> ResponseVO<T> prepareErrorResponse(int statusCode, String errorCode, String errorMessage) {

		ResponseVO<T> response = new ResponseVO<>();

		response.setData(null);
		response.setStatusCode(statusCode);
		response.setErrorCode(errorCode);
		response.setMessage(errorMessage);
		response.setStatus(false);
		return response;
	}

	/**
	 * This method is used to prepare a response
	 * @param <T>
	 * @param data
	 * @param statusCode
	 * @param message
	 * @return Response
	 */
	protected <T> ResponseVO<T> prepareResponse(T data, int statusCode, String message) {

		ResponseVO<T> response = new ResponseVO<>();

		response.setData(data);
		response.setStatusCode(statusCode);
		response.setMessage(message);

		return response;
	}
}
