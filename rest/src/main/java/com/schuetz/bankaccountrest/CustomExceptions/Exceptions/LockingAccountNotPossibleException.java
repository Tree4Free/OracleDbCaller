package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class LockingAccountNotPossibleException extends BaseException{
    public LockingAccountNotPossibleException(SQLException e, String message) {
        super(e, message);
    }
}
