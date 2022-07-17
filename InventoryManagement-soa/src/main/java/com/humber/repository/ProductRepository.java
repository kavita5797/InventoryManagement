package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{

}
