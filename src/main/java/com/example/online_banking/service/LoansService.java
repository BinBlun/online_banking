package com.example.online_banking.service;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
import com.example.online_banking.repository.custom.TransactionRepositoryCustom;
import com.example.online_banking.rest.model.LoansInput;
import com.example.online_banking.rest.model.LoansOutput;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoansService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansPackageRepository loansPackageRepository;

    @Autowired CardRepository cardRepository;

//    public LoansOutput doLoans(Authentication authentication, LoansInput loansInput) throws DataInvalidException {
////        //tìm số SSN của người muốn vay tiền
////        User user = userRepository.findBySSNNumber(loansInput.getSNN());
////        Account account = accountRepository.findFirstByUserId(user.getId());
////        Card card = cardRepository.findByUserID(user.getId());
////
////        //tạo 1 loans và lưu vào trong database
////        Loans loans = new Loans();
////        //lưu ID của gói loans đã đắng ký
////        loans.setLoansid(loansInput.getLoansPackageID());
////
////        //lưu số tiền đã vay
////        loans.setLoansAmountTaken(loansInput.getAmount());
////
////        //lưu số tiền cần phải trả (tính thêm cả lãi suất)
////        LoansPackage loansPackage = loansPackageRepository.getById(loansInput.getLoansPackageID());
////        int amountTaken = Integer.parseInt(loansInput.getAmount());
////        int duration = Integer.parseInt(loansPackage.getDuration());
////        int interestRate = (int)loansPackage.getInterestRate();
////        int LoansAmountRepaid = ((amountTaken*interestRate)/duration) + amountTaken;
////        loans.setLoansAmountRepaid(String.valueOf(LoansAmountRepaid));
////
////        loansRepository.save(loans);
////        return LoansOutput.builder().status(Constants.STATUS_SUCCESS).build();
//        String userName = authentication.getName();
//
//    }
}
