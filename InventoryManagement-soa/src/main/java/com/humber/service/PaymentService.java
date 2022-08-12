package com.humber.service;

import java.util.List;

import com.humber.model.Payment;

public interface PaymentService {

	/**
	 * This service is used for bill payment.
	 * @param id
	 * @param amount
	 * @param paymentType
	 * @return isBillPaid
	 */
	boolean billPayment(String id, double amount, String paymentType);

	
	/**
	 * This service is used to get all payments.
	 * @return payment
	 */
	List<Payment> getAllPayments();

}
