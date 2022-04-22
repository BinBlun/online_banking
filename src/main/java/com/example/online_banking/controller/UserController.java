package com.example.online_banking.controller;

import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private RegisterService service;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoansPackageRepository loansPackageRepository;

    @RequestMapping("")
    public String viewCustomerHome(Model model){
        User user = userRepository.getById(1L);
        model.addAttribute("currentUser", user);
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

        return "TransferTransaction";
    }

    @RequestMapping("/moneyLoans/{id}")
    public String moneyLoans(@PathVariable(value = "id") Long id, Model model){
//        Tìm loans mà người dùng đã đăng ký
        User user = userRepository.getById(id);
        List<Loans> loans = loansRepository.findBySSN(user.getSsn());
        model.addAttribute("loans", loans);

//        Hiện lên LoansPackage
        List<LoansPackage> loansPackages = loansPackageRepository.findAll();
        model.addAttribute("loansPackages", loansPackages);
        return "moneyLoans";
    }
}
