package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	public User findByEmail(String emailId);

}
