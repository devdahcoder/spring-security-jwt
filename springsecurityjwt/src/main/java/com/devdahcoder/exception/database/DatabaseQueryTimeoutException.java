package com.devdahcoder.exception.database;

import org.springframework.dao.QueryTimeoutException;

public class DatabaseQueryTimeoutException extends QueryTimeoutException {

    public DatabaseQueryTimeoutException(String msg) {

        super(msg);

    }

    public DatabaseQueryTimeoutException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
