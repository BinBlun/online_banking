package com.example.online_banking.controller;

import com.example.online_banking.model.Account;
import com.example.online_banking.model.Card;
import com.example.online_banking.model.Transaction;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.AccountRepository;
import com.example.online_banking.repository.CardRepository;
import com.example.online_banking.repository.TransactionRepository;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.service.RegisterService;
import com.example.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private RegisterService service;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

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
    public String transferMoney(@PathVariable(value = "id") Long id ,
                                Model model) {
        Account account1 = accountRepository.getById(id);
        model.addAttribute("account", account1);

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
//        return "redirect:/transferSuccess";
        return "TransferTransaction";
    }

    @PostMapping(value = "/doTransferMoney")
    public String doTransferMoney(TransferTransactionInput input,     Model model){
        Account account1 = accountRepository.getById(1L);
        model.addAttribute("account", account1);

        List<Account> account = accountRepository.findByAccountNumber(input.getAccountNumber());
        Account recipient = account.get(0);
        if(CollectionUtils.isEmpty(account) || account.size() > 1){
            return "TransferTransaction";
        } else {
            if(Double.valueOf(input.getAmount()) > account1.getCurrentBalance().doubleValue()){
                System.out.println("Cant send");
                return "TransferTransaction";
            } else {
                account1.setCurrentBalance(account1.getCurrentBalance().subtract(new BigDecimal(input.getAmount())));
                accountRepository.save(account1);
                recipient.setCurrentBalance(recipient.getCurrentBalance().add(new BigDecimal(input.getAmount())));
                accountRepository.save(recipient);
                return "TransferSuccess";
            }
        }
    }

    @RequestMapping("/transferSuccess")
    public String transferSuccess(Model model) {
        return "TransferSuccess";
    }

}
