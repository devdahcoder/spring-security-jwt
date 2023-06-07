package com.devdahcoder.exception.database;

import org.springframework.dao.DuplicateKeyException;

public class DatabaseDuplicateKeyException extends DuplicateKeyException {

    public DatabaseDuplicateKeyException(String msg) {

        super(msg);

    }

    public DatabaseDuplicateKeyException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
