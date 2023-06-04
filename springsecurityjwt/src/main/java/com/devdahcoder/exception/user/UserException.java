package com.devdahcoder.exception.user;

import org.springframework.dao.DataAccessException;

public class UserException extends DataAccessException {

	public UserException(String message) {

		super(message);

	}

	public UserException(String message, Throwable cause) {

		super(message, cause);

	}

}
