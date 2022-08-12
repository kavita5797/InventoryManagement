package com.humber.service;

import java.util.List;

import com.humber.model.RecievingStock;

public interface ReceivingStockService {

	/**
	 * This service is called to save the recievingstock.
	 * @param receivedStock
	 * @return receivingStock
	 */
	RecievingStock save(RecievingStock receivedStock);

	/**
	 * This service is used to get all the stock details
	 * @return ReceivingStock
	 */
	List<RecievingStock> getAllStockDetails();

	/**
	 * This service is used to get count of pending payment.
	 * @return pendingpayment
	 */
	int getAllPendingPaymentCount();

}
