package com.schuetz.bankaccountrest.service;

import com.schuetz.bankaccountrest.persistence.OracleSqlCaller;
import com.schuetz.bankaccountrest.CustomExceptions.ErrorCodes;
import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final ErrorCodes errorCodes;
    private final OracleSqlCaller oracleSqlCaller;

    @Override
    public long createAccount(long customerId, long limit) {
        try {
            return oracleSqlCaller.createAccount(customerId, limit);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Customer %d not found", customerId));
        }
    }

    @Override
    public long depositMoney(long accountId, long amount) {
        try {
            return oracleSqlCaller.depositMoney(accountId, amount);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Deposit of %d€ for account id %d failed", amount, accountId));
        }
    }

    @Override
    public long withdrawMoney(long accountId, long amount) {
        try {
            return oracleSqlCaller.withdrawMoney(accountId, amount);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Withdraw of %d€ for account id %d failed", amount, accountId));
        }
    }

    @Override
    public boolean lockAccount(long accountId) {
        try {
            return oracleSqlCaller.lockAccount(accountId);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Locking of account id %d failed", accountId));
        }
    }

    @Override
    public boolean deleteAccount(long accountId) {
        log.info("Deleting account: {}", accountId);
        try {
            return oracleSqlCaller.deleteAccount(accountId);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Deleting account %d failed", accountId));
        }
    }

    @Override
    public long getBalance(long accountId) {
        log.info("Getting balance for id {}", accountId);
        try {
            long balances = oracleSqlCaller.getAccountBalance(accountId);
            log.info("Balance for {}: {}€", accountId, balances);
            return balances;
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Getting balance for account id %d failed", accountId));
        }
    }

    @Override
    public boolean transfer(long from, long to, long amount) {

        log.info("Transferring {}€ from {} to {}", amount, from, to);
        try {
            return oracleSqlCaller.transferMoney(from, to, amount);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Transfering money from account %d to account %d failed", from, to));
        }
    }

    @Override
    public List<Transfer> showBalanceHistory(long accountId) {
        try {
            log.info("Showing balance history: {}", accountId);
            return oracleSqlCaller.getAccountBalanceHistory(accountId);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Showing balance history for account %d failed", accountId));
        }
    }

    @Override
    public List<AccountHistory> showAccountHistory(long accountId) {
        try {
            log.info("Showing account history: {}", accountId);
            return oracleSqlCaller.getAccountHistory(accountId);
        } catch (SQLException e) {
            throw errorCodes.getException(e, format("Showing account history for account %d failed", accountId));
        }
    }
}
