package com.devdahcoder.exception.api;

public class ApiAlreadyExistException extends RuntimeException {

    public ApiAlreadyExistException(String message) {

        super(message);

    }

    public ApiAlreadyExistException(String message, Throwable cause) {

        super(message, cause);

    }
}
