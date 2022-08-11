package com.humber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, String> {

	Merchant findByEmail(String email);

	Page<Merchant> findAll(Pageable pageable);

	Page<Merchant> findByEmailIgnoreCaseContainsOrNameIgnoreCaseContains(String searchText, String searchText2,
			Pageable pageable);

}
