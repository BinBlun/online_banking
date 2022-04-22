package com.example.online_banking.service;

import com.example.online_banking.model.Account;
import com.example.online_banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    @Autowired
    AccountRepository accountRepository;

    public Account transferMoneyToSomeoneElse(String amount, Account account1, Account account2){
        account1.setCurrentBalance(account1.getCurrentBalance().subtract(new BigDecimal(amount)));
        accountRepository.save(account1);
        account2.setCurrentBalance(account2.getCurrentBalance().add(new BigDecimal(amount)));
        accountRepository.save(account2);
        return account1;
    }
}
