package com.humber.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humber.common.vo.DataTableVO;
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

	@Override
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setIsActive(1);
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public DataTableVO<User> getAllUsersByFilter(String searchText, int offset, int size, String sortField,
			int sortOrder) {

		Direction direction = sortOrder == 1 ? Direction.ASC : Direction.DESC;
		Sort sort = Sort.by(direction, sortField);
		Pageable pageable = PageRequest.of(offset, size, sort);
		Page<User> userList = null;

		DataTableVO<User> userData = new DataTableVO<>();

		userData.setFirst(pageable.getPageNumber());
		userData.setRows(pageable.getPageSize());
		if (searchText != null && searchText != "") {
			userList = userRepository.findByEmailIgnoreCaseContainsOrFirstNameIgnoreCaseContains(
					searchText, searchText, pageable);
		} else {
			userList = userRepository.findAll(pageable);
		}

		userData.setResult(userList.getContent());
		userData.setTotalRecords((int) userList.getTotalElements());
		return userData;
	}

}
