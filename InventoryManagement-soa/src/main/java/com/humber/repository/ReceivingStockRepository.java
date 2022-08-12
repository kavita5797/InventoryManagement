package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.RecievingStock;

@Repository
public interface ReceivingStockRepository extends CrudRepository<RecievingStock, String> {

	int countByPendingPayment(int i);

}
