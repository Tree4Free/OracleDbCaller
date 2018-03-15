package com.schuetz.bankaccountrest.persistence;

import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface OracleSqlCaller {



    long createAccount(long customerId, long limit) throws SQLException;

    long getAccountBalance(long accountId) throws SQLException;

    boolean deleteAccount(long accountId) throws SQLException;

    boolean lockAccount(long accountId) throws SQLException;

    boolean transferMoney(long fromId, long toId, long amount) throws SQLException;

    List<AccountHistory> getAccountHistory(long accountId) throws SQLException;

    List<Transfer> getAccountBalanceHistory(long accountId) throws SQLException;

    long depositMoney(long accountId, long amount) throws SQLException;

    long withdrawMoney(long accountId, long amount) throws SQLException;
}
