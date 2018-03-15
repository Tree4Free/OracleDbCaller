package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class NoHistoryFoundException extends BaseException {
    public NoHistoryFoundException(SQLException cause, String errorMessage) {
        super(cause, errorMessage);
    }
}
