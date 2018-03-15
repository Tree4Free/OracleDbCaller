/*
package com.schuetz.bankaccountrest.persistence;

import com.schuetz.commons.domain.entity.AccountHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.schuetz.bankaccountrest.procedures.Procedures.*;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OracleSqlCallerImpl implements OracleSqlCaller {

    private final CallableStatementProvider statementProvider;

    public long getAccountBalance(long accountId) throws SQLException {

        return statementProvider.prepareStatement(SHOW_ACCOUNT_BALANCE_PROCEDURE, new HashMap<String, Object>() {{
            put("bank_account_id", accountId);
        }}).execute().getResult(Long.class);
    }

    public boolean transferMoney(long fromId, long toId, long amount) throws SQLException {

        statementProvider.prepareStatement(TRANSFER_MONEY, new HashMap<String, Object>() {{
            put("from_account_id", fromId);
            put("to_account_id", toId);
            put("transfer_amount", amount);
        }}).execute();

        return true;
    }

    public long createAccount(long customerId) throws SQLException {

        return statementProvider.prepareStatement(CREATE_ACCOUNT, new HashMap<String, Object>() {{
            put("bank_customer_id", customerId);
        }}).execute().getResults(Long.class);
    }

    public boolean deleteAccount(long accountId) throws SQLException {

        statementProvider.prepareStatement(DELETE_ACCOUNT, new HashMap<String, Object>() {{
            put("bank_account_id", accountId);
        }}).execute();

        return true;
    }

    public List<AccountHistory> getAccountHistory(long accountId) throws SQLException {

        return statementProvider.prepareStatement(SHOW_ACCOUNT_HISTORY_PROCEDURE, new HashMap<String, Object>() {{
            put("bank_account_id", accountId);
        }}).execute().getResults(AccountHistory.class);
    }
}
*/
