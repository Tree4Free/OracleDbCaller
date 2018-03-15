/*
package com.schuetz.bankaccountrest.persistence.foundation;

import com.schuetz.bankaccountrest.procedures.Procedure;
import lombok.Builder;
import lombok.Data;

import java.sql.CallableStatement;
import java.sql.SQLException;

@Data
@Builder
public class CustomCallableStatement {
    private final CallableStatement callableStatement;
    private final Procedure procedure;

    public CustomCallableStatementResult execute() throws SQLException {
        boolean hadResults = callableStatement.execute();

        while (hadResults) {
            callableStatement.getResultSet();
            hadResults = callableStatement.getMoreResults();
        }

        return CustomCallableStatementResult.builder()
                .callableStatement(callableStatement)
                .procedure(procedure)
                .build();
    }
}*/
