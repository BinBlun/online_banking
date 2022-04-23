package com.example.online_banking.controller;

import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
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
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoansPackageRepository loansPackageRepository;

    @RequestMapping("")
    public String viewCustomerHome(Model model) {
        User user = userRepository.getById(1L);
        model.addAttribute("currentUser", user);
        return "customerHome";
    }

    @RequestMapping("/viewBalance/{id}")
    public String showViewBalance(@PathVariable(value = "id") Long id, Model model) {
        Card card = cardRepository.getById(id);
        model.addAttribute("card", card);
        return "viewBalancePage";
    }

    @RequestMapping("/transferMoney/{id}")
    public String transferMoney(@PathVariable(value = "id") Long id,
                                Model model) {
        Account account1 = accountRepository.getById(id);
        model.addAttribute("account", account1);

//        Transaction transaction = new Transaction();
//        model.addAttribute("transaction", transaction);
//        return "redirect:/transferSuccess";
        return "TransferTransaction";
    }

    @PostMapping(value = "/doTransferMoney")
    public ResponseEntity<String> doTransferMoney(@RequestBody TransferTransactionInput input, Model model) {
        //tìm tài khoản mà muốn rút tiền
        Account account1 = accountRepository.getById(1L);
        model.addAttribute("account", account1);

        //tìm tài khoản muốn chuyển tiền cho
        List<Account> account = accountRepository.findByAccountNumber(input.getAccountNumber());

        if (CollectionUtils.isEmpty(account) || account.size() > 1) {
            return ResponseEntity.badRequest().body("Account not exist");
        } else {
            //tạo transaction mới
            Transaction transaction = new Transaction();

            //nếu tài khoản chuyển tiền lớn hơn só tiền muốn chuyển thì không chuyển được tiền
            if (Double.valueOf(input.getAmount()) > account1.getCurrentBalance().doubleValue()) {
                System.out.println("Cant send");
                transaction.setTransactionAmount(new BigDecimal(input.getAmount()));
                transaction.setRecipientAccountID(Long.parseLong(input.getAccountNumber()));
                transaction.setTransactionType("TRANSFER");
                transaction.setStatus("FAIL");
                transaction.setDescription(input.getDescription());
                transaction.setAccountId(account1);
                Date transactionDate = new Date();
                transaction.setTransactionDate(transactionDate);
                transactionRepository.save(transaction);
                return ResponseEntity.badRequest().body("Amount is insufficient");
            } else {
                Account recipient = account.get(0);
                //trừ tiền của tài khoản gửi
                account1.setCurrentBalance(account1.getCurrentBalance().subtract(new BigDecimal(input.getAmount())));
                accountRepository.save(account1);
                //cộng tiền vào tài khoản nhận
                recipient.setCurrentBalance(recipient.getCurrentBalance().add(new BigDecimal(input.getAmount())));
                accountRepository.save(recipient);


                //Ấy lên database
                transaction.setTransactionAmount(new BigDecimal(input.getAmount()));
                transaction.setRecipientAccountID(Long.parseLong(input.getAccountNumber()));
                transaction.setTransactionType("TRANSFER");
                transaction.setStatus("SUCCESS");
                transaction.setDescription(input.getDescription());
                transaction.setAccountId(account1);
                Date transactionDate = new Date();
                transaction.setTransactionDate(transactionDate);
                transactionRepository.save(transaction);

                return ResponseEntity.ok("Success");
            }
        }
    }

    @RequestMapping("transactionSuccess")
    public String transactionSuccess(Model model){
        return "transactionSuccess";
    }

    @RequestMapping("/moneyLoans/{id}")
    public String moneyLoans(@PathVariable(value = "id") Long id, Model model) {
//        Tìm loans mà người dùng đã đăng ký
        User user = userRepository.getById(id);
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

    @RequestMapping("/chooseLoansPackage")
    public String chooseLoansPackage() {
        return "moneyLoans";
    }


    @RequestMapping("/transferSuccess")
    public String transferSuccess(Model model) {
        return "transactionSuccess";
    }

    @RequestMapping("/withdrawMoney/{id}")
    public String withdrawMoney(@PathVariable(value = "id") Long id,
                                Model model) {
        //tìm tài khoản mà muốn rút tiền
        Account account1 = accountRepository.getById(id);
        model.addAttribute("account1", account1);

//        Transaction transaction = new Transaction();
//        model.addAttribute("transaction", transaction);
//        return "redirect:/transferSuccess";
        return "withdrawMoney";
    }

    @PostMapping(value = "/doWithdrawMoney")
    public String doWithdrawMoney(TransferTransactionInput input, Model model) {
        //lấy người muốn rút tiền
        Account account1 = accountRepository.getById(1L);
        model.addAttribute("account", account1);

        //Tạo transaction mới
        Transaction transaction = new Transaction();

        if (Double.valueOf(input.getAmount()) > account1.getCurrentBalance().doubleValue()) {
            System.out.println("Cant withdraw");
            return "withdrawMoney";
        } else {
            account1.setCurrentBalance(account1.getCurrentBalance().subtract(new BigDecimal(input.getAmount())));
            //lưu vào database
            accountRepository.save(account1);
            return "transactionSuccess";
        }

    }

    @RequestMapping("/depositMoney/{id}")
    public String depositMoney(@PathVariable(value = "id") Long id,
                                Model model) {
        //tìm tài khoản mà muốn cho tiền vào
        Account account1 = accountRepository.getById(id);
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
