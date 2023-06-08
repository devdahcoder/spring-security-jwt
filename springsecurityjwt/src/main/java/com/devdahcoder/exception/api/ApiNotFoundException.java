package com.devdahcoder.exception.api;

import org.springframework.dao.EmptyResultDataAccessException;

public class ApiNotFoundException extends EmptyResultDataAccessException {

    public ApiNotFoundException(int expectedSize) {

        super(expectedSize);

    }

    public ApiNotFoundException(String msg, int expectedSize) {

        super(msg, expectedSize);

    }

    public ApiNotFoundException(String msg, int expectedSize, Throwable ex) {

        super(msg, expectedSize, ex);

    }

}
