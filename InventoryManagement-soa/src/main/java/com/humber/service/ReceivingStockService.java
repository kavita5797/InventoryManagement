package com.humber.service;

import com.humber.model.RecievingStock;

public interface ReceivingStockService {

	RecievingStock save(RecievingStock receivedStock);

	boolean billPayment(String id, double amount, String paymentType);

}
