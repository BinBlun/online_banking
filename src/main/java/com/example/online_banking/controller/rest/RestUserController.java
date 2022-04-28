package com.example.online_banking.controller.rest;

import com.example.online_banking.rest.model.ResponseData;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.rest.model.TransferTransactionOutput;
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

    @PostMapping(value = "/doTransferMoney")
    public ResponseData<TransferTransactionOutput> doTransferMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws Exception{
        return ResponseData.ok(transactionService.doTransferMoney(authentication, input));
    }
}
