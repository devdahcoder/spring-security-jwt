package com.devdahcoder.exception.api;

import org.springframework.dao.DataAccessException;

public class ApiException extends DataAccessException {

	public ApiException(String message) {

		super(message);

	}

	public ApiException(String message, Throwable cause) {

		super(message, cause);

	}

}
