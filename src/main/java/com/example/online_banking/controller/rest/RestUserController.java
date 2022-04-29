package com.example.online_banking.controller.rest;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.rest.model.*;
import com.example.online_banking.service.LoansService;
import com.example.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/rest")
public class RestUserController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoansService loansService;

    @PostMapping(value = "/doTransferMoney")
    public ResponseData<TransferTransactionOutput> doTransferMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws Exception{
        return ResponseData.ok(transactionService.doTransferMoney(authentication, input));
    }

    @PostMapping(value = "/doWithdrawMoney")
    public ResponseData<TransferTransactionOutput> doWithdrawMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(transactionService.doWithdrawMoney(authentication, input));
    }

    @PostMapping(value = "/doLoans")
    public ResponseData<LoansOutput> doLoans(
            @RequestBody LoansInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(loansService.doLoans(authentication, input));
    }

}