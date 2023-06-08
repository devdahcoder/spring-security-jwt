package com.devdahcoder.exception.database;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

public class DatabaseIncorrectResultSizeException extends IncorrectResultSizeDataAccessException {

    public DatabaseIncorrectResultSizeException(int expectedSize) {

        super(expectedSize);

    }

    public DatabaseIncorrectResultSizeException(int expectedSize, int actualSize) {

        super(expectedSize, actualSize);

    }

    public DatabaseIncorrectResultSizeException(String msg, int expectedSize) {

        super(msg, expectedSize);

    }

    public DatabaseIncorrectResultSizeException(String msg, int expectedSize, Throwable ex) {

        super(msg, expectedSize, ex);

    }

    public DatabaseIncorrectResultSizeException(String msg, int expectedSize, int actualSize) {

        super(msg, expectedSize, actualSize);

    }

    public DatabaseIncorrectResultSizeException(String msg, int expectedSize, int actualSize, Throwable ex) {

        super(msg, expectedSize, actualSize, ex);

    }

}
