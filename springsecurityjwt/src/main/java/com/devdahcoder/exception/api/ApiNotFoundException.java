package com.devdahcoder.exception.api;

public class ApiNotFoundException extends RuntimeException {

    public ApiNotFoundException(String message) {

        super(message);

    }

    public ApiNotFoundException(String message, Throwable cause) {

        super(message, cause);

    }

}
