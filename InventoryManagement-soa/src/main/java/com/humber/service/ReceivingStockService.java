package com.humber.service;

import java.util.List;

import com.humber.model.RecievingStock;

public interface ReceivingStockService {

	RecievingStock save(RecievingStock receivedStock);

	List<RecievingStock> getAllStockDetails();

	int getAllPendingPaymentCount();

}
