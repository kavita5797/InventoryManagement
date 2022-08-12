package com.humber.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.model.Product;
import com.humber.model.RecievingStock;
import com.humber.repository.ProductRepository;
import com.humber.repository.ReceivingStockRepository;
import com.humber.service.ReceivingStockService;

@Service
public class ReceivingStockServiceImpl implements ReceivingStockService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReceivingStockRepository receivingStockRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public RecievingStock save(RecievingStock receivedStock) {
		receivedStock.setId(UUID.randomUUID().toString());
		receivedStock.setPendingPayment(1);
		receivedStock = receivingStockRepository.save(receivedStock);
		logger.info("Stock received::" + receivedStock.toString());

		// update product price and product quantity
		Optional<Product> product = productRepository.findById(receivedStock.getProductId());
		if (product.isPresent()) {
			product.get().setProductprice(receivedStock.getUnitPrice());
			product.get().setProductquality(receivedStock.getQuantity());
			productRepository.save(product.get());
		}

		return receivedStock;
	}

	@Override
	public List<RecievingStock> getAllStockDetails() {
		logger.info("GET ALL Stock received::");
		return (List<RecievingStock>) receivingStockRepository.findAll();
	}

}
