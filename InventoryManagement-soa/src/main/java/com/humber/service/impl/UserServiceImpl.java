package com.humber.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humber.common.utils.CommonUtility;
import com.humber.common.vo.DataTableVO;
import com.humber.common.vo.LoginVO;
import com.humber.model.User;
import com.humber.repository.UserRepository;
import com.humber.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User authenticate(LoginVO loginVO) {
		User user = userRepository.findByEmail(loginVO.getEmail());
		if(user != null) {
			if(CommonUtility.decrypt(user.getPassword()).equals(loginVO.getPassword())) {
				return user;
			}
			return null;
		}
		return null;
	}

	@Override
	public User getUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public User getUserByEmailId(String emailId) {
		return userRepository.findByEmail(emailId);
	}

	@Override
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setIsActive(1);
		String password = CommonUtility.encrypt(user.getPassword());
		user.setPassword(password);
		user = userRepository.save(user);
		logger.info("User created::" + user.toString());
		return user;
	}

	@Override
	public User updateUser(User user) {
		User oldUser = getUserById(user.getId());
		if (oldUser != null) {
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setEmail(user.getEmail());
			oldUser.setCity(user.getCity());
			oldUser.setCountry(user.getCountry());
			user = userRepository.save(oldUser);
			logger.info("User updated::" + user.toString());
			return user;
		}
		return null;
	}

	@Override
	public boolean deleteUser(String userId) {
		if (getUserById(userId) != null) {
			userRepository.deleteById(userId);
			logger.info("User deleted::" + userId);
			return true;
		}
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
			userList = userRepository.findByEmailIgnoreCaseContainsOrFirstNameIgnoreCaseContains(searchText, searchText,
					pageable);
		} else {
			userList = userRepository.findAll(pageable);
		}

		userData.setResult(userList.getContent());
		userData.setTotalRecords((int) userList.getTotalElements());
		return userData;
	}

	@Override
	public long getTotalCount() {
		return userRepository.count();
	}

	

}
