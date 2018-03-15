package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(SQLException e, String errorMessage) {
        super(e, errorMessage);
    }
}
