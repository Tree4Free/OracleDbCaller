package com.schuetz.commons.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    long ACCOUNT_ID;
    long CUSTOMER_ID;
    long BALANCE;
    boolean IS_OPEN;
    boolean IS_DELETED;
}
