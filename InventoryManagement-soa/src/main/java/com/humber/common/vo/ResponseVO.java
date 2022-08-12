package com.humber.common.vo;

import lombok.Data;

@Data
public class ResponseVO<T> {
	private int statusCode;
	private String errorCode = "";
	private String message;
	private T data;
	private boolean status;

}
