package com.example.online_banking.service;

import com.example.online_banking.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    public void transferMoneyToSomeoneElse(String amount, Account account1, Account account2){
        account1.setCurrentBalance(account1.getCurrentBalance().subtract(new BigDecimal(amount)));
        account2.setCurrentBalance(account2.getCurrentBalance().add(new BigDecimal(amount)));
    }
}
