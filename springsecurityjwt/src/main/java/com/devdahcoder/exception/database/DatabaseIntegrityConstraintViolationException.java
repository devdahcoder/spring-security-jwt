package com.devdahcoder.exception.database;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseIntegrityConstraintViolationException extends DataIntegrityViolationException {

    public DatabaseIntegrityConstraintViolationException(String msg) {

        super(msg);

    }

    public DatabaseIntegrityConstraintViolationException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
