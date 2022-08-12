package com.humber.service;

public interface PaymentService {

	boolean billPayment(String id, double amount, String paymentType);

}
