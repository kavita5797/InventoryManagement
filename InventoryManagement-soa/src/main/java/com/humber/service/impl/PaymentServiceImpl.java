package com.humber.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.model.Payment;
import com.humber.model.RecievingStock;
import com.humber.repository.PaymentRepository;
import com.humber.repository.ReceivingStockRepository;
import com.humber.service.PaymentService;

@Service
public class PaymentServiceImpl  implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReceivingStockRepository receivingStockRepository;

	@Override
	public boolean billPayment(String id, double amount, String paymentType) {
		Optional<RecievingStock> recievingStock = receivingStockRepository.findById(id);
		if(recievingStock.isPresent()) {
			
			Payment payment = new Payment();
			payment.setId(UUID.randomUUID().toString());
			payment.setPaidAmount(amount);
			payment.setPaymentDate(new Date());
			payment.setPaymentType(paymentType);
			payment.setStockId(id);
			payment.setTotalAmount(recievingStock.get().getTotalPrice());
			
			payment = paymentRepository.save(payment);
			if(payment != null ) {
				RecievingStock recStock = recievingStock.get();
				recStock.setTotalPaidAmount(recStock.getTotalPaidAmount() + amount);
				if(recStock.getTotalPaidAmount() == recStock.getTotalPrice()) {
					recStock.setPendingPayment(0);
				}
				receivingStockRepository.save(recStock);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<Payment> getAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}

}
