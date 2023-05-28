package com.devdahcoder.user.exception;

import org.springframework.dao.DataAccessException;

public class UserNotFoundException extends DataAccessException {

	public UserNotFoundException(String message) {

		super(message);

	}

	public UserNotFoundException(String message, Throwable cause) {

		super(message, cause);

	}
}
