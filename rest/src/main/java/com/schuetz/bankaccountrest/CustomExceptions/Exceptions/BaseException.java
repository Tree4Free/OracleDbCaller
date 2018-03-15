package com.schuetz.bankaccountrest.CustomExceptions.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    BaseException(SQLException cause, String errorMessage) {
        super(cause);
        this.errorCode = cause.getErrorCode();
        this.errorMessage = errorMessage;
    }
}
