package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String>{

}
