package com.humber.service;

import java.util.List;

import com.humber.common.vo.DataTableVO;
import com.humber.common.vo.LoginVO;
import com.humber.model.User;

public interface UserService {

	/**
	 * This service is to get user by email id.
	 * 
	 * @param emailId
	 * @return user
	 */
	User getUserByEmailId(String emailId);

	/**
	 * This service is to save user.
	 * 
	 * @param user
	 * @return user
	 */
	User saveUser(User user);

	/**
	 * This service is to update user.
	 * 
	 * @param user
	 * @return user
	 */
	User updateUser(User user);

	/**
	 * This service is to delete user.
	 * 
	 * @param userId
	 * @return isDeleted
	 */
	boolean deleteUser(String userId);

	/**
	 * This service is to get all users.
	 * 
	 * @return users
	 */
	List<User> getAllUsers();

	/**
	 * This service is to get all users by pagination and filters.
	 * 
	 * @param searchText
	 * @param offset
	 * @param size
	 * @param sortField
	 * @param sortOrder
	 * @return users
	 */
	DataTableVO<User> getAllUsersByFilter(String searchText, int offset, int size, String sortField, int sortOrder);

	/**
	 * This service is to get user by id.
	 * 
	 * @param id
	 * @return user
	 */
	User getUserById(String id);

	/**
	 * This service is to authenticate user.
	 * 
	 * @param loginVO
	 * @return user
	 */
	User authenticate(LoginVO loginVO);

	/**
	 * This service is to get total count.
	 * 
	 * @return count
	 */
	long getTotalCount();
}
