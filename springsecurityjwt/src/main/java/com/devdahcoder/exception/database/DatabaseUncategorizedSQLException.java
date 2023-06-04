package com.devdahcoder.exception.database;

import org.springframework.jdbc.UncategorizedSQLException;

import java.sql.SQLException;

public class DatabaseUncategorizedSQLException extends UncategorizedSQLException {


    /**
     * Constructor for UncategorizedSQLException.
     *
     * @param task name of current task
     * @param sql  the offending SQL statement
     * @param ex   the root cause
     */
    public DatabaseUncategorizedSQLException(String task, String sql, SQLException ex) {

        super(task, sql, ex);

    }

}
