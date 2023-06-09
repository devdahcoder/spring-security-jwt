package com.devdahcoder.exception.database;

import org.springframework.jdbc.BadSqlGrammarException;

import java.sql.SQLException;

public class DatabaseBadSqlGrammarException extends BadSqlGrammarException {
    /**
     * Constructor for BadSqlGrammarException.
     *
     * @param task name of the current task
     * @param sql  the offending SQL statement
     * @param ex   the root cause
     */
    public DatabaseBadSqlGrammarException(String task, String sql, SQLException ex) {

        super(task, sql, ex);

    }

}
