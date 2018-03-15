package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class UnexpectedException extends BaseException {

    public UnexpectedException(SQLException e, String message) {
        super(e, message);
    }
}
