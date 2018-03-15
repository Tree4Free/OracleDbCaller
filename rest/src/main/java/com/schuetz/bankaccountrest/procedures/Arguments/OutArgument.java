package com.schuetz.bankaccountrest.procedures.Arguments;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Type;
import java.sql.Types;

@Data
@Builder
public class OutArgument {
    private final String name;
    private final int    type;

    public OutArgument(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public OutArgument(String name) {
        this.name = name;
        this.type = Types.BIGINT;
    }
}
