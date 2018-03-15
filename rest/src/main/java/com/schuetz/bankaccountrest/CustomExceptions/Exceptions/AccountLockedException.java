package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class AccountLockedException extends BaseException {
    public AccountLockedException(SQLException cause, String errorMessage) {
        super(cause, errorMessage);
    }
}
