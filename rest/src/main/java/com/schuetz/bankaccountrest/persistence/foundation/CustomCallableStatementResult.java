/*
package com.schuetz.bankaccountrest.persistence.foundation;

import com.schuetz.bankaccountrest.procedures.Arguments.OutArgument;
import com.schuetz.bankaccountrest.procedures.Procedure;
import com.schuetz.commons.domain.entity.AccountHistory;
import lombok.Builder;
import lombok.Data;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CustomCallableStatementResult {

    private final CallableStatement callableStatement;
    private final Procedure         procedure;

    public <T> List<?> getResults(Class<T> clazz) {
        return procedure.getOut_arguments().stream().map(x -> getResult(x, clazz)).collect(Collectors.toList());
    }

    public Long getLongResults() {
        return procedure.getOut_arguments().stream().map(x -> getResult(x, Long.class)).collect(Collectors.toList()).get(0);
    }

    private <T> T getResult(OutArgument arg, Class<T> clazz) {

        if (arg.getType()== OracleTypes.CURSOR) return getCursorResultSet(arg.getName(), AccountHistory.class);

        try {
            return callableStatement.getObject(arg.getName(), clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> getCursorResultSet(String arg, Class clazz) {
        try {
            List<T> list= Collections.emptyList();
            ResultSet rs = (ResultSet) callableStatement.getObject(arg);
            while (rs.next()){
//                list.add(rs.)
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
*/
