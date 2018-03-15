package com.schuetz.bankaccountrest.rest;

import com.schuetz.bankaccountrest.service.BankAccountService;
import com.schuetz.commons.domain.dto.requests.CreateAccountRequest;
import com.schuetz.commons.domain.dto.requests.InteractionRequest;
import com.schuetz.commons.domain.dto.requests.TransferRequest;
import com.schuetz.commons.domain.dto.responses.*;
import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/api/account")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        log.info("Receive request on POST /api/account");
        log.debug(request.toString());
        return ResponseEntity.ok(AccountResponse.builder()
                .accountId(bankAccountService.createAccount(request.getCustomerId(), request.getLimit()))
                .build());
    }

    @DeleteMapping("/api/account/{id}")
    public ResponseEntity<SuccessResponse> deleteAccount(@PathVariable("id") long id) {
        log.info("Receive request on DELETE /api/account/{}", id);
        return ResponseEntity.ok(SuccessResponse.builder()
                .success(bankAccountService.deleteAccount(id))
                .build());
    }

    @GetMapping("/api/account/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable("id") long id) {
        log.info("Receive request on GET /api/account/{}/balance", id);
        long balance = bankAccountService.getBalance(id);
        return ResponseEntity.ok(BalanceResponse.builder()
                .accountId(id)
                .balance(balance)
                .build());
    }

    @PostMapping("/api/account/transfer")
    public ResponseEntity<SuccessResponse> transferMoney(@RequestBody TransferRequest request) {
        log.info("Receive request on POST /api/account/transfer");
        log.debug(request.toString());
        boolean success = bankAccountService.transfer(request.getFromId(), request.getToId(), request.getAmount());
        return ResponseEntity.ok(SuccessResponse.builder()
                .success(success)
                .build());
    }

    @GetMapping("/api/account/{id}/balance/history")
    public ResponseEntity<BalanceHistoryResponse> showBalanceHistory(@PathVariable("id") long id) {
        log.info("Receive request on GET /api/account/{id}/balance/history", id);
        List<Transfer> transfers = bankAccountService.showBalanceHistory(id);
        return ResponseEntity.ok(BalanceHistoryResponse.builder()
                .history(transfers)
                .build());
    }

    @GetMapping("/api/account/{id}/history")
    public ResponseEntity<AccountHistoryResponse> showAccountHistory(@PathVariable("id") long id) {
        log.info("Receive request on GET /api/account/{id}/history", id);
        List<AccountHistory> accountHistory = bankAccountService.showAccountHistory(id);
        return ResponseEntity.ok(AccountHistoryResponse.builder()
                .history(accountHistory)
                .build());
    }

    @PostMapping("/api/account/{id}/withdraw")
    public ResponseEntity<BalanceResponse> withdrawMoney(@PathVariable("id") long id, @RequestBody InteractionRequest withdrawRequest) {

        log.info("Receive request on POST /api/account/{}/withdraw", id);
        return ResponseEntity.ok(BalanceResponse.builder()
                .accountId(id)
                .balance(bankAccountService.withdrawMoney(id, withdrawRequest.getAmount()))
                .build());
    }

    @PostMapping("/api/account/{id}/deposit")
    public ResponseEntity<BalanceResponse> depositMoney(@PathVariable("id") long id, @RequestBody InteractionRequest depositRequest) {
        log.info("Receive request on POST /api/account/{}/deposit", id);
        return ResponseEntity.ok(BalanceResponse.builder()
                .accountId(id)
                .balance(bankAccountService.depositMoney(id, depositRequest.getAmount()))
                .build());
    }

    @GetMapping("/api/account/{id}/lock")
    public ResponseEntity<SuccessResponse> lockAccount(@PathVariable("id") long id) {
        log.info("Receive request on GET /api/account/{}/lock", id);
        boolean success = bankAccountService.lockAccount(id);
        return ResponseEntity.ok(SuccessResponse.builder()
                .success(success)
                .build());
    }

}
