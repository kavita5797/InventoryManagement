package com.humber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	public User findByEmail(String emailId);

	public Page<User> findByEmailIgnoreCaseContainsOrFirstNameIgnoreCaseContains(String searchText, String searchText2,
			Pageable pageable);

	public Page<User> findAll(Pageable pageable);

}
