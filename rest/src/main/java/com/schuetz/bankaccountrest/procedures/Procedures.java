package com.schuetz.bankaccountrest.procedures;

import com.schuetz.bankaccountrest.procedures.Arguments.InArgument;
import com.schuetz.bankaccountrest.procedures.Arguments.OutArgument;
import oracle.jdbc.OracleTypes;

import java.sql.JDBCType;
import java.sql.Types;

import static com.schuetz.bankaccountrest.procedures.Procedure.inArgumentsList;
import static com.schuetz.bankaccountrest.procedures.Procedure.outArgumentsList;

public interface Procedures {
    Procedure DELETE_ACCOUNT = new Procedure("DELETE_ACCOUNT",
                                             inArgumentsList(new InArgument("bank_account_id")),
                                             outArgumentsList());

    Procedure CREATE_ACCOUNT = new Procedure("CREATE_ACCOUNT",
                                             inArgumentsList(new InArgument("bank_customer_id")),
                                             outArgumentsList(new OutArgument("bank_account_id")));

    Procedure LOCK_ACCOUNT = new Procedure("LOCK_ACCOUNT",
                                           inArgumentsList(new InArgument("bank_account_id")),
                                           outArgumentsList());

    Procedure SHOW_ACCOUNT_BALANCE_PROCEDURE = new Procedure("SHOW_ACCOUNT_BALANCE_PROCEDURE",
                                                             inArgumentsList(new InArgument("bank_account_id")),
                                                             outArgumentsList(new OutArgument("bank_account_balance")));

    Procedure TRANSFER_MONEY = new Procedure("TRANSFER_MONEY",
                                             inArgumentsList(new InArgument("from_account_id"),
                                                             new InArgument("to_account_id"),
                                                             new InArgument("transfer_amount")),
                                             outArgumentsList());

    Procedure SHOW_ACCOUNT_HISTORY_PROCEDURE = new Procedure("SHOW_ACCOUNT_HISTORY_PROCEDURE",
                                                             inArgumentsList(new InArgument("bank_account_id")),
                                                             outArgumentsList(new OutArgument("output_history", OracleTypes.CURSOR)));
}