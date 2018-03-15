package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException(SQLException e, String message) {
        super(e, message);
    }
}
