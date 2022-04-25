package com.example.online_banking.controller;

import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
import com.example.online_banking.rest.model.CommonResponse;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.service.RegisterService;
import com.example.online_banking.service.TransactionService;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private BankRepository bankRepository;

    @RequestMapping("")
    public String viewCustomerHome(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "customerHome";
    }

    @RequestMapping("/viewBalance/{id}")
    public String showViewBalance(@PathVariable(value = "id") Long id, Model model) {
        Card card = cardRepository.getById(id);
        model.addAttribute("card", card);
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

    @PostMapping(value = "/doTransferMoney")
    public ResponseEntity<CommonResponse> doTransferMoney(@RequestBody TransferTransactionInput input, Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        CommonResponse response = new CommonResponse();
        //tìm tài khoản mà muốn rút tiền
        Account debitAccount = accountRepository.findFirstByUserId(user.getId());
        //tìm tài khoản muốn chuyển tiền cho
        Account creditAccount = accountRepository.findByAccountNumberAndBankId(input.getAccountNumber(), input.getBankReceiveId());
        BigDecimal amount = new BigDecimal(input.getAmount());

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amount);
        transaction.setAccountId(debitAccount.getAccountId());
        transaction.setDescription(input.getDescription());
        Date transactionDate = new Date();
        transaction.setTransactionDate(transactionDate);
        transaction.setTransactionType("TRANSFER");
        if (creditAccount == null) {
            // neu tai khoan nhan tien khong ton tai
            transaction.setStatus("FAIL");
            transaction.setDescription("Account not exist");
            transactionRepository.save(transaction);
            response.setStatus(Constants.STATUS_ERROR);
            response.setErrorCode("001");
            response.setErrorDesc("Account not exist");
            return ResponseEntity.badRequest().body(response);
        } else if (Double.valueOf(input.getAmount()) > debitAccount.getCurrentBalance().doubleValue()) {
            //nếu tài khoản chuyển tiền lớn hơn só tiền muốn chuyển thì không chuyển được tiền
            transaction.setRecipientAccountID(creditAccount.getAccountId());
            transaction.setStatus("FAIL");
            transaction.setDescription("Account balance is insufficient");
            transactionRepository.save(transaction);
            response.setStatus(Constants.STATUS_ERROR);
            response.setErrorCode("002");
            response.setErrorDesc("Account balance is insufficient");
            return ResponseEntity.badRequest().body(response);
        } else {
            //trừ tiền của tài khoản gửi
            debitAccount.setCurrentBalance(debitAccount.getCurrentBalance().subtract(amount));
            accountRepository.save(debitAccount);
            //cộng tiền vào tài khoản nhận
            creditAccount.setCurrentBalance(creditAccount.getCurrentBalance().add(amount));
            accountRepository.save(creditAccount);
            //Lưu log giao dịch lên database
            transaction.setRecipientAccountID(creditAccount.getAccountId());
            transaction.setTransactionAmount(amount);
            transaction.setStatus("SUCCESS");
            transactionRepository.save(transaction);

            response.setStatus(Constants.STATUS_SUCCESS);
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping("transactionSuccess")
    public String transactionSuccess(Model model) {
        return "transactionSuccess";
    }

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
