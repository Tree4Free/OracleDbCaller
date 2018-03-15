package com.schuetz.bankaccount.service;

import com.schuetz.commons.domain.dto.requests.CreateAccountRequest;
import com.schuetz.commons.domain.dto.requests.InteractionRequest;
import com.schuetz.commons.domain.dto.requests.TransferRequest;
import com.schuetz.commons.domain.dto.responses.BalanceResponse;
import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Commands {

    private final RestClient restClient;

    @ShellMethod("Method to get the balance.")
    public long getBalance(long id) {
        return restClient.getBalance(id).getBalance();
    }

    @ShellMethod("Method to transfer money.")
    public String transferMoney(long fromId, long toId, long amount) {
        return restClient.transferMoney(TransferRequest.builder()
                .fromId(fromId)
                .toId(toId)
                .amount(amount)
                .build()).isSuccess()?"Success":"Failure";
    }

    @ShellMethod("Method to create an account.")
    public String createAccount(long customerId, @ShellOption(defaultValue="-100") long limit) {
        return "Created Account with id: "+restClient.createAccount(CreateAccountRequest.builder().customerId(customerId).limit(limit).build()).getAccountId();
    }

    @ShellMethod("Method to delete an account.")
    public String deleteAccount(long accountId) {
        return restClient.deleteAccount(accountId).isSuccess()?"Success":"Failure";
    }

    @ShellMethod("Method to show an accounts transfer history.")
    public List<Transfer> showBalanceHistory(long accountId) {
        return restClient.showBalanceHistory(accountId).getHistory();
    }

    @ShellMethod("Method to show an accounts activity history.")
    public List<AccountHistory> showAccountHistory(long accountId) {
        return restClient.showAccountHistory(accountId).getHistory();
    }

    @ShellMethod("Method to deposit money.")
    public String depositMoney(long accountId, long amount) {
        return "Balance: "+restClient.depositMoney(accountId, InteractionRequest.builder()
                .amount(amount)
                .build()).getBalance();
    }

    @ShellMethod("Method to withdraw money.")
    public String withdrawMoney(long accountId, long amount) {
        return "Balance: "+restClient.withdrawMoney(accountId, InteractionRequest.builder()
                .amount(amount)
                .build()).getBalance();
    }

    @ShellMethod("Method to lock an account.")
    public String lockAccount(long accountId) {
        return restClient.lockAccount(accountId).isSuccess()?"Success":"Failure";
    }
}
