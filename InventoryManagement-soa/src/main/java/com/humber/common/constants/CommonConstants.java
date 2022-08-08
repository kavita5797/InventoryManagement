package com.humber.common.constants;

public class CommonConstants {

	public class ErrorCode {
		public final static String NO_DATA_FOUND = "E5001";
		public final static String NO_DATA_SAVED = "E5002";
		public final static String NO_DATA_UPDATED = "E5003";
		public final static String NO_DATA_DELETED = "E5004";

		public final static String USER_NOT_FOUND = "E5005";
		public final static String USER_ALREADY_EXIST = "E5006";
	}

	public class ErrorCodeMessage {
		public final static String NO_DATA_FOUND = "Data not found.";
		public final static String NO_DATA_SAVED = "Data is not saved.";
		public final static String NO_DATA_UPDATED = "Data is not updated.";
		public final static String NO_DATA_DELETED = "Data is not deleted.";
		public final static String USER_NOT_FOUND = "User for given id does not exist.";
		public final static String USER_ALREADY_EXIST = "User with this email id already exist.";
	}
}
