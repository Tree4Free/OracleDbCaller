package com.schuetz.bankaccount.service;

import com.schuetz.bankaccount.Profile;
import com.schuetz.commons.domain.dto.requests.CreateAccountRequest;
import com.schuetz.commons.domain.dto.requests.InteractionRequest;
import com.schuetz.commons.domain.dto.requests.TransferRequest;
import com.schuetz.commons.domain.dto.responses.*;
import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;


public interface RestClient {

    @RequestLine("POST /api/account")
    @Headers({"Content-Type: application/json"})
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    @RequestLine("DELETE /api/account/{id}")
    SuccessResponse deleteAccount(@Param("id") long id);

    @RequestLine("GET /api/account/{id}/balance")
    BalanceResponse getBalance(@Param("id") long id);

    @RequestLine("POST /api/account/transfer")
    @Headers({"Content-Type: application/json"})
    SuccessResponse transferMoney(TransferRequest request);

    @RequestLine("GET /api/account/{id}/balance/history")
    BalanceHistoryResponse showBalanceHistory(@Param("id") long id);

    @RequestLine("GET /api/account/{id}/history")
    AccountHistoryResponse showAccountHistory(@Param("id") long id);

    @RequestLine("POST /api/account/{id}/deposit")
    BalanceResponse depositMoney(@Param("id") long id, InteractionRequest interactionRequest);

    @RequestLine("POST /api/account/{id}/withdraw")
    BalanceResponse withdrawMoney(@Param("id") long id, InteractionRequest interactionRequest);

    @RequestLine("GET /api/account/{id}/lock")
    SuccessResponse lockAccount(@Param("id") long id);

}
