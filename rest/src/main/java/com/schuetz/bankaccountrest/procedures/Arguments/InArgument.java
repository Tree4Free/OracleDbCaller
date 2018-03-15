package com.schuetz.bankaccountrest.procedures.Arguments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InArgument {
    private final String name;
    private final Class type;

    public InArgument(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public InArgument(String name) {
        this.name = name;
        this.type = Long.class;
    }
}
