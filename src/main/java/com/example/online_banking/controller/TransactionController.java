package com.example.online_banking.controller;

import com.example.online_banking.model.Transaction;
import com.example.online_banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping("/transferMoney")
    public String transferMoney(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "TransferTransaction";
    }


}
