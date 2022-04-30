package com.example.online_banking.service;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.model.*;
import com.example.online_banking.repository.SavingPackageRepository;
import com.example.online_banking.repository.SavingRepositoy;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.rest.model.ErrorCode;
import com.example.online_banking.rest.model.LoansMoneyOutput;
import com.example.online_banking.rest.model.SavingMoneyInput;
import com.example.online_banking.rest.model.SavingMoneyOutput;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SavingMoneyService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingPackageRepository savingPackageRepository;

    @Autowired
    private SavingRepositoy savingRepositoy;

    public SavingMoneyOutput doSavingMoney(Authentication authentication, SavingMoneyInput input) throws DataInvalidException {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);

        if (input.getAmount() == null){
            throw new DataInvalidException(ErrorCode.NO_AMOUNT);
        } else if(input.getSavingPackageID() == null) {
            throw new DataInvalidException(ErrorCode.NO_SAVING_PACKAGE);
        } else {
            Saving saving = new Saving();
            saving.setUserId(user.getId());
            saving.setSavingPackageId(input.getSavingPackageID());
            saving.setSavingAmountTaken(input.getAmount());
            saving.setStatus(Constants.STATUS_WAITING);

            SavingPackage savingPackage = savingPackageRepository.getById(input.getSavingPackageID());
            BigDecimal amountProfit = BigDecimal.valueOf(Double.valueOf(input.getAmount().toString()) * savingPackage.getInterestRate() * savingPackage.getDuration());
            saving.setSavingAmounProfit(input.getAmount().add(amountProfit));
            saving.setRegistrationDate(new Date());

            savingRepositoy.save(saving);
            return SavingMoneyOutput.builder().status(Constants.STATUS_SUCCESS).build();
        }


    }
}
