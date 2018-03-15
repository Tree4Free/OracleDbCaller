package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import java.sql.SQLException;

public class DeletingAccountNotPossibleException extends BaseException{
    public DeletingAccountNotPossibleException(SQLException cause, String errorMessage) {
        super(cause, errorMessage);
    }
}
