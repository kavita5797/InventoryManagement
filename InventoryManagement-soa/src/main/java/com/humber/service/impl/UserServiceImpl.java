package com.humber.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.model.User;
import com.humber.repository.UserRepository;
import com.humber.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User getUserByEmailId(String emailId) {
		return userRepository.findByEmail(emailId);
	}

}
