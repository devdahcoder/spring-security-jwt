package com.devdahcoder.exception.database;

import org.springframework.dao.TypeMismatchDataAccessException;

public class DatabaseTypeMismatchException extends TypeMismatchDataAccessException {

    public DatabaseTypeMismatchException(String msg) {

        super(msg);

    }

    public DatabaseTypeMismatchException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
