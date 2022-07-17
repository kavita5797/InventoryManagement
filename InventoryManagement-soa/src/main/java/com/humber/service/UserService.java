package com.humber.service;

import com.humber.model.User;

public interface UserService {

	User getUserByEmailId(String emailId);

	User saveUser(User user);

	User updateUser(User user);

	boolean deleteUser(String userId);

	User getAllUsers();
}
