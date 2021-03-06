package com.humber.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.common.constants.CommonConstants;
import com.humber.common.vo.DataTableVO;
import com.humber.common.vo.ResponseVO;
import com.humber.model.User;
import com.humber.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("user")
@Api(tags = "User API", description = "API for Users")
public class UserController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@GetMapping
	@ApiOperation(value = "Get all users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<List<User>> getAllUsers() throws Exception {
		logger.info("REST request to get all users: {}");

		List<User> user = userService.getAllUsers();
		if (user != null) {
			return prepareSuccessResponse(user);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@GetMapping("/filter")
	@ApiOperation(value = "Get all users by search and pagination")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<DataTableVO<User>> getAllGroups(
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size,
			@RequestParam(value = "sortField", required = false, defaultValue = "email") String sortField,
			@RequestParam(value = "sortOrder", required = false, defaultValue = "1") int sortOrder) throws Exception {
//		logger.info("GET All user, Params  : searchText=" + searchText + " offset=" + searchText + " offset=" + offset
//				+ " size=" + size + " sortField=" + sortField + " sortOrder=" + sortOrder);

		DataTableVO<User> data = userService.getAllUsersByFilter(searchText, offset, size, sortField, sortOrder);
		if (data != null) {
			return prepareSuccessResponse(data);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@GetMapping(value = "getUserByEmail")
	@ApiOperation(value = "Get user by email id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 404, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class) })
	public ResponseVO<User> getUserByEmailId(@RequestParam(value = "emailId", required = true) String emailId)
			throws Exception {
		logger.info("REST request to get parent user by email id: {}", emailId);

		User user = userService.getUserByEmailId(emailId);
		if (user != null) {
			return prepareSuccessResponse(user);
		}
		return prepareErrorResponse(HttpStatus.NOT_FOUND.value(), CommonConstants.ErrorCode.NO_DATA_FOUND,
				CommonConstants.ErrorCodeMessage.NO_DATA_FOUND);

	}

	@PostMapping(value = "signup")
	@ApiOperation(value = "Create user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<User> create(@RequestBody User user) throws Exception {
		logger.info("CREATE new user : ", user.toString());
		User newUser = userService.saveUser(user);
		if (newUser != null) {
			return prepareSuccessResponse(newUser);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_SAVED,
				CommonConstants.ErrorCodeMessage.NO_DATA_SAVED);
	}

	@PutMapping(value = "updateUser")
	@ApiOperation(value = "Update user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5003-NO_DATA_UPDATED", response = ResponseVO.class) })
	public ResponseVO<User> update(@RequestBody User user) throws Exception {
		logger.info("Update new user : ", user.toString());
		User newUser = userService.updateUser(user);
		if (newUser != null) {
			return prepareSuccessResponse(newUser);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_UPDATED,
				CommonConstants.ErrorCodeMessage.NO_DATA_UPDATED);
	}

	@DeleteMapping(value = "deleteUser")
	@ApiOperation(value = "Delete user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5002-NO_DATA_SAVED", response = ResponseVO.class) })
	public ResponseVO<Boolean> delete(@RequestParam String userId) throws Exception {
		logger.info("Delete new user : ", userId);
		boolean deleted = userService.deleteUser(userId);
		if (deleted) {
			return prepareSuccessResponse(deleted);
		}
		return prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.ErrorCode.NO_DATA_DELETED,
				CommonConstants.ErrorCodeMessage.NO_DATA_DELETED);
	}

}
