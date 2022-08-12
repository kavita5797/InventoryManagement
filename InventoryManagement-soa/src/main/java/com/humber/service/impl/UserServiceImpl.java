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

	/**
	 * 
	 *This repository is used for the user operation.
	 */
	@Autowired
	UserRepository userRepository;

	
	
	/**
	 * 
	 *This method is used to authenticate the user
	 *@param loginVO
	 *@return user
	 */
	@Override
	public User authenticate(LoginVO loginVO) {
		logger.info("User autentication::" + loginVO.toString());
		User user = userRepository.findByEmail(loginVO.getEmail().toLowerCase());
		if (user != null) {
			if (CommonUtility.decrypt(user.getPassword()).equals(loginVO.getPassword())) {
				logger.info("User autentication success.");
				return user;
			}
			logger.info("User autentication fail.");
			return null;
		}
		logger.info("User autentication fail. No user found.");
		return null;
	}

	/**
	 *This method is to get user by id.
	 *@param id
	 *@return user
	 */
	@Override
	public User getUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			logger.info("GET User by id found.");
			return user.get();
		}
		logger.info("GET User by id not found.");
		return null;
	}

	/**
	 * This method is to get the user by their emailid
	 *@param emailid
	 *@return user
	 */
	@Override
	public User getUserByEmailId(String emailId) {
		logger.info("GET User by emailid::");
		return userRepository.findByEmail(emailId.toLowerCase());
	}

	/**
	 *This method is used to save user.
	 *@param user
	 *@return user
	 */
	@Override
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setIsActive(1);
		String email = user.getEmail().toLowerCase();
		user.setEmail(email);
		String password = CommonUtility.encrypt(user.getPassword());
		user.setPassword(password);
		user = userRepository.save(user);
		logger.info("User created::" + user.toString());
		return user;
	}

	/**
	 *This method is used to update user.
	 *@param user
	 *@return user
	 */
	@Override
	public User updateUser(User user) {
		User oldUser = getUserById(user.getId());
		if (oldUser != null) {
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setEmail(user.getEmail().toLowerCase());
			oldUser.setCity(user.getCity());
			oldUser.setCountry(user.getCountry());
			user = userRepository.save(oldUser);
			logger.info("User updated::" + user.toString());
			return user;
		}
		return null;
	}

	/**
	 * This method is used to delete user.
	 * @param userId
	 *@return isDeleted
	 */
	@Override
	public boolean deleteUser(String userId) {
		if (getUserById(userId) != null) {
			userRepository.deleteById(userId);
			logger.info("User deleted::" + userId);
			return true;
		}
		return false;
	}

	/**
	 *This method is used to get all users
	 *@return user
	 *
	 */
	@Override
	public List<User> getAllUsers() {
		logger.info("GET All users::");
		return (List<User>) userRepository.findAll();
	}

	/**
	 * This method is used to get all users by pagination and filter
	 * @param searchText
	 * @param offset
	 * @param size
	 * @param sortField
	 * @param sortOrder
	 * @return users
	 *
	 */
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
		logger.info("GET All user pagination::" + userData.toString());
		return userData;
	}

	/**
	 * This method is used to get total count.
	 *@return count
	 */
	@Override
	public long getTotalCount() {
		logger.info("GET All users count::" + userRepository.count());
		return userRepository.count();
	}

}
