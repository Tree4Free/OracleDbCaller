package com.schuetz.bankaccountrest.procedures;

import com.schuetz.bankaccountrest.procedures.Arguments.InArgument;
import com.schuetz.bankaccountrest.procedures.Arguments.OutArgument;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Procedure {
    private final String            name;
    private final List<InArgument>  in_arguments;
    private final List<OutArgument> out_arguments;

    public static List<InArgument> inArgumentsList(InArgument... args){
        return Arrays.asList(args);
    }

    public static List<OutArgument> outArgumentsList(OutArgument... args){
        return Arrays.asList(args);
    }
}
