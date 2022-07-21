package com.humber.service;

import java.util.List;

import com.humber.common.vo.DataTableVO;
import com.humber.model.User;

public interface UserService {

	User getUserByEmailId(String emailId);

	User saveUser(User user);

	User updateUser(User user);

	boolean deleteUser(String userId);

	List<User> getAllUsers();

	DataTableVO<User> getAllUsersByFilter(String searchText, int offset, int size, String sortField, int sortOrder);
}
