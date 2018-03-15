package com.schuetz.bankaccountrest.persistence;

import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleOracleSqlCaller implements OracleSqlCaller {

    private final DataSource dataSource;

    @Override
    public long createAccount(long customerId, long limit) throws SQLException {

        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.CREATE_ACCOUNT(?,?,?)}");

        callableStatement.setLong("bank_customer_id", customerId);
        callableStatement.setLong("bank_account_limit", limit);

        callableStatement.registerOutParameter("bank_account_id", OracleTypes.BIGINT);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return callableStatement.getLong("bank_account_id");
    }

    @Override
    public long getAccountBalance(long accountId) throws SQLException {

        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.SHOW_ACCOUNT_BALANCE_PROCEDURE(?,?)}");

        callableStatement.setLong("bank_account_id", accountId);

        callableStatement.registerOutParameter("bank_account_balance", OracleTypes.BIGINT);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return callableStatement.getLong("bank_account_balance");
    }

    @Override
    public boolean deleteAccount(long accountId) throws SQLException {

        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.DELETE_ACCOUNT(?)}");

        callableStatement.setLong("bank_account_id", accountId);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return true;
    }

    @Override
    public boolean lockAccount(long accountId) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.LOCK_ACCOUNT(?)}");

        callableStatement.setLong("bank_account_id", accountId);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return true;
    }

    @Override
    public boolean transferMoney(long fromId, long toId, long amount) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.TRANSFER_MONEY(?,?,?)}");

        callableStatement.setLong("from_account_id", fromId);
        callableStatement.setLong("to_account_id", toId);
        callableStatement.setLong("transfer_amount", amount);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return true;
    }

    @Override
    public List<AccountHistory> getAccountHistory(long accountId) throws SQLException {

        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.SHOW_ACCOUNT_HISTORY_PROCEDURE(?,?)}");

        callableStatement.setLong("bank_account_id", accountId);

        callableStatement.registerOutParameter("output_history", OracleTypes.CURSOR);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        ResultSet resultSet = callableStatement.getObject("output_history", ResultSet.class);

        List<AccountHistory> accountHistoryList = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            long bankAccountId = resultSet.getLong("account_id");
            long date = resultSet.getTimestamp("action_time").getTime();
            String action = resultSet.getString("action");
            accountHistoryList.add(AccountHistory.builder()
                    .id(id)
                    .accountId(bankAccountId)
                    .date(date)
                    .action(action)
                    .build());
        }

        return accountHistoryList;
    }

    @Override
    public List<Transfer> getAccountBalanceHistory(long accountId) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.SHOW_BALANCE_HISTORY_PROCEDURE(?,?)}");

        callableStatement.setLong("bank_account_id", accountId);

        callableStatement.registerOutParameter("output_history", OracleTypes.CURSOR);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        ResultSet resultSet = callableStatement.getObject("output_history", ResultSet.class);

        List<Transfer> transferList = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            long fromId = resultSet.getLong("account_id_from");
            long toId = resultSet.getLong("account_id_to");
            long timestamp = resultSet.getTimestamp("transfer_time").getTime();
            long amount = resultSet.getLong("amount");
            transferList.add(Transfer.builder()
                    .id(id)
                    .fromId(fromId)
                    .toId(toId)
                    .amount(amount)
                    .timestamp(timestamp)
                    .build());
        }

        return transferList;
    }

    @Override
    public long depositMoney(long accountId, long amount) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.DEPOSIT_MONEY(?,?,?)}");

        callableStatement.setLong("bank_account_id", accountId);
        callableStatement.setLong("deposit_amount", amount);
        callableStatement.registerOutParameter("account_balance", Types.BIGINT);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return callableStatement.getLong("account_balance");
    }

    @Override
    public long withdrawMoney(long accountId, long amount) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall("{CALL BANKACCOUNT.WITHDRAW_MONEY(?,?,?)}");

        callableStatement.setLong("bank_account_id", accountId);
        callableStatement.setLong("withdraw_amount", amount);
        callableStatement.registerOutParameter("account_balance", Types.BIGINT);

        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return callableStatement.getLong("account_balance");
    }
}
