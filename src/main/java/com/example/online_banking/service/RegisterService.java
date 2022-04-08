package com.example.online_banking.service;

import com.example.online_banking.model.Register;
import com.example.online_banking.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    public Register save(Register model) {
        model.setRole("CUSTOMER");
        return registerRepository.save(model);
    }
}
