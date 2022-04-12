package com.example.online_banking.service;

import com.example.online_banking.model.Account;
import com.example.online_banking.model.Customer;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.AccountRepository;
import com.example.online_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class RegisterService {
    @Autowired
    UserRepository registerRepository;

    @Autowired
    AccountRepository accountRepository;

    public Customer save(Customer model) {
        model.setRole("CUSTOMER");
        Customer customer = registerRepository.save(model);

        String accNum = randomNumber(9);
        do {
            if (accountRepository.findByAccountNumber(accNum) != null) {
                accNum = randomNumber(9);
            } else {
                break;
            }
        } while (true);

        Account account = new Account();
        account.setAccountNumber(randomNumber(9));
        account.setCustomerId(customer.getId());

        Date currentDate = new Date();
        account.setActiveDate(currentDate);
        accountRepository.save(account);

        // tao account

        return customer;
    }

    private String randomNumber(int size) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < size ; i++) {
            a.append(new Random().nextInt(9));
        }
        System.out.println(a.toString());
        return a.toString();
    }
}
