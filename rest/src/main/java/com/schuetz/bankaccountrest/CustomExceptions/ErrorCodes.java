package com.schuetz.bankaccountrest.CustomExceptions;

import com.schuetz.bankaccountrest.CustomExceptions.Exceptions.*;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class ErrorCodes {
    //20001 - User not found
    //20002 - Account not found
    //20003 - Locking failed
    //20004 - Deleting failed
    //20005 - No history available
    //20006 - Account is locked
    //20007 - Account is deleted
    public BaseException getException(SQLException e, String message) throws BaseException {
        return getException(e.getErrorCode(), e, message);
    }

    private BaseException getException(int errorCode, SQLException e, String message) throws BaseException {
        switch (errorCode) {
            case 20001:
                return new UserNotFoundException(e, message);
            case 20002:
                return new AccountNotFoundException(e, message);
            case 20003:
                return new LockingAccountNotPossibleException(e, message);
            case 20004:
                return new DeletingAccountNotPossibleException(e, message);
            case 20005:
                return new NoHistoryFoundException(e, message);
            case 20006:
                return new AccountLockedException(e, message);
            case 20007:
                return new AccountDeletedException(e, message);
            default:
                return new UnexpectedException(e, "Unknown exception");
        }
    }
}
