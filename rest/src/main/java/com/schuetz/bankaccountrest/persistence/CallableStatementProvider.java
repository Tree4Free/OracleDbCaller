/*
package com.schuetz.bankaccountrest.persistence;

import com.schuetz.bankaccountrest.persistence.foundation.CustomCallableStatement;
import com.schuetz.bankaccountrest.procedures.Arguments.InArgument;
import com.schuetz.bankaccountrest.procedures.Arguments.OutArgument;
import com.schuetz.bankaccountrest.procedures.Procedure;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CallableStatementProvider {

    private final DataSource dataSource;
    private final String packageName = "BANKACCOUNT";

    public CustomCallableStatement prepareStatement(Procedure procedure, Map<String, ?> args) throws SQLException {
        CallableStatement callableStatement = dataSource.getConnection().prepareCall(prepareProcedureName(procedure));
        List<InArgument>  inArguments      = procedure.getIn_arguments();

        inArguments.forEach(x->setInParameter(callableStatement, x.getName(), args.get(x.getName())));

        setOutParameters(callableStatement, procedure.getOut_arguments());

        return CustomCallableStatement.builder().callableStatement(callableStatement).procedure(procedure).build();
    }

    private void setInParameter(CallableStatement callableStatement, String x, Object arg) {

        int i = 1;

        try {
            callableStatement.setObject(x, arg);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOutParameter(CallableStatement callableStatement, String x, int type) {
        try {
            callableStatement.registerOutParameter(x, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOutParameters(CallableStatement callableStatement, List<OutArgument> out_arguments) {
        out_arguments.forEach(x -> setOutParameter(callableStatement, x.getName(), x.getType()));
    }


    private String getArgs(int args) {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append("?");
        for (int i = 1; i < args; i++) {
            stringBuilder.append(",?");
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    private String prepareProcedureName(Procedure procedure) {
        return format("{call %s.%s}", packageName, getProcedureName(procedure));
    }

    private String getProcedureName(Procedure procedure) {
        return procedure.getName() + getArgs(procedure.getIn_arguments().size() + procedure.getOut_arguments().size());
    }


}
*/
