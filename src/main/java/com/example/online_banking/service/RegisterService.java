package com.example.online_banking.service;

import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    UserRepository registerRepository;

    public User save(User model) {
        model.setRole("CUSTOMER");
        return registerRepository.save(model);
    }
}
