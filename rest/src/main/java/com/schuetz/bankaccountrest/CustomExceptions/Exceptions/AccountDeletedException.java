package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class AccountDeletedException extends BaseException {
    public AccountDeletedException(SQLException cause, String errorMessage) {
        super(cause, errorMessage);
    }
}
