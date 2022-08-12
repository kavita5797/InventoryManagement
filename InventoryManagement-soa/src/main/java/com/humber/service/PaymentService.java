package com.humber.service;

import java.util.List;

import com.humber.model.Payment;

public interface PaymentService {

	boolean billPayment(String id, double amount, String paymentType);

	List<Payment> getAllPayments();

}
