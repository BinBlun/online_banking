package com.example.online_banking.controller;

import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoansPackageRepository loansPackageRepository;

    @Autowired
    private BankRepository bankRepository;

    @RequestMapping("")
    public String viewCustomerHome(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "customerHome";
    }

    @RequestMapping("/profile")
    public String viewProfile(Authentication authentication, Model model){
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("user", user);
        return "customerProfile";
    }

    @RequestMapping("/viewBalance")
    public String showViewBalance(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
//        Card card = cardRepository.getById(account.getAccountId());
        model.addAttribute("account", account);
        return "viewBalancePage";
    }

    @RequestMapping("/transferMoney")
    public String transferMoney(Authentication authentication,
                                Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
        model.addAttribute("account", account);

        List<Bank> bankList = bankRepository.findAll();
        model.addAttribute("bankList", bankList);
        return "TransferTransaction";
    }

//    @RequestMapping("transactionSuccess")
//    public String transactionSuccess(Model model) {
//        return "transactionSuccess";
//    }

    @RequestMapping("/moneyLoans")
    public String moneyLoans(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);

//        Tìm loans mà người dùng đã đăng ký
        List<Loans> loans = loansRepository.findBySSN(user.getSsn());
        model.addAttribute("loans", loans);

//        Hiện lên LoansPackage
        List<LoansPackage> loansPackages = loansPackageRepository.findAll();
        model.addAttribute("loansPackages", loansPackages);
        return "moneyLoans";
    }

    @RequestMapping("/doLoans")
    public String doLoans() {
        return "moneyLoans";
    }


    @RequestMapping("/transferSuccess")
    public String transferSuccess(Model model) {
        return "transactionSuccess";
    }

    @RequestMapping("/withdrawMoney")
    public String withdrawMoney(Authentication authentication,
                                Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
        model.addAttribute("account", account);
        return "withdrawMoney";
    }

    @RequestMapping("/depositMoney")
    public String depositMoney(Authentication authentication,
                               Model model) {
        //tìm tài khoản mà muốn cho tiền vào
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account1 = accountRepository.findFirstByUserId(user.getId());
        model.addAttribute("account1", account1);

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
//        return "redirect:/transferSuccess";
        return "depositMoney";
    }

    @PostMapping(value = "/doDepositMoney")
    public String doDepositMoney(TransferTransactionInput input, Model model) {
        //tìm tài khoản muốn cho tiền vào
        Account account1 = accountRepository.getById(1L);
        model.addAttribute("account", account1);

        if (Double.valueOf(input.getAmount()) > account1.getCurrentBalance().doubleValue()) {
            System.out.println("Cant deposit");
            return "depositMoney";
        } else {
            //cộng tiền vào tài khoản cho tiền vào
            account1.setCurrentBalance(account1.getCurrentBalance().add(new BigDecimal(input.getAmount())));
            //lưu vào database
            accountRepository.save(account1);
            return "transactionSuccess";
        }
    }
}
