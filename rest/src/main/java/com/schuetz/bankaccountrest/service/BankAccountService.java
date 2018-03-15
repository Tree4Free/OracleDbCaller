package com.schuetz.bankaccountrest.service;

import com.schuetz.bankaccountrest.CustomExceptions.Exceptions.BaseException;
import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankAccountService {

    long createAccount(long customerId, long limit) throws BaseException;

    long depositMoney(long accountId, long amount);

    long withdrawMoney(long accountId, long amount);

    boolean lockAccount(long accountId);

    boolean deleteAccount(long accountId);

    long getBalance(long accountId);

    boolean transfer(long from, long to, long amount);

    List<Transfer> showBalanceHistory(long accountId);

    List<AccountHistory> showAccountHistory(long accountId);
}
