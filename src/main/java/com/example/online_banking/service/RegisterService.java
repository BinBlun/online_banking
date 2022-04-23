package com.example.online_banking.service;

import com.example.online_banking.model.Account;
import com.example.online_banking.model.Card;
import com.example.online_banking.model.User;
import com.example.online_banking.model.UserRole;
import com.example.online_banking.repository.AccountRepository;
import com.example.online_banking.repository.CardRepository;
import com.example.online_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class RegisterService {
    @Autowired
    UserRepository registerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    public User save(User model) {
        model.setRole("CUSTOMER");
        User customer = registerRepository.save(model);

        String accNum = randomNumber(9);
        do {
            if (!CollectionUtils.isEmpty(accountRepository.findByAccountNumber(accNum))) {
                accNum = randomNumber(9);
            } else {
                break;
            }
        } while (true);
//        // tao account
        Account account = new Account();
        account.setAccountNumber(accNum);
        account.setCurrentBalance(BigDecimal.valueOf(0));
        account.setUserId(customer);

        Date accountCurrentDate = new Date();
        account.setActiveDate(accountCurrentDate);
        accountRepository.save(account);
//
//        //tạo card
        Card card = new Card();
        card.setCardNumber(randomNumber(11));
        card.setCurrentBalance(BigDecimal.valueOf(0));
        card.setUserId(customer.getId());

        Date cardCurrentDate = new Date();
        card.setCartDate(cardCurrentDate);
        cardRepository.save(card);

//        tạo role
        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserId(customer.getId());
        return customer;
    }


    private String randomNumber(int size) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < size; i++) {
            a.append(new Random().nextInt(9));
        }
        return a.toString();
    }
}
