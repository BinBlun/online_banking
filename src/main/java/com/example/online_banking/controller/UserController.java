package com.example.online_banking.controller;

import com.example.online_banking.model.Account;
import com.example.online_banking.model.Card;
import com.example.online_banking.model.Transaction;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.AccountRepository;
import com.example.online_banking.repository.CardRepository;
//import com.example.online_banking.repository.TransactionRepository;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private RegisterService service;

    @Autowired
    private CardRepository cardRepository;

//    @Autowired
//    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("")
    public String viewCustomerHome(Model model){
        return "customerHome";
    }

    @RequestMapping("/viewBalance/{id}")
    public String showViewBalance(@PathVariable(value = "id") Long id, Model model){
        Card card = cardRepository.getById(id);
        model.addAttribute("card", card);
        return "viewBalancePage";
    }

    @RequestMapping("/transferMoney/{id}")
    public String transferMoney(@PathVariable(value = "id") Long id ,Model model) {
        Account account1 = accountRepository.getById(id);
        model.addAttribute("account", account1);

//        Transaction transaction = new Transaction();
//        model.addAttribute("transaction", transaction);


        return "TransferTransaction";
    }


}
