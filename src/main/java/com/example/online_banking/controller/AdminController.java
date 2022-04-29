package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String adminHome(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "adminHome";
    }

    @RequestMapping("/manage-customer")
    public String manageCustomer() {
        return "admin/manage-customer";
    }
}
