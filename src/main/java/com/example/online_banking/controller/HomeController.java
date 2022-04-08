package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RegisterService service;

// HOME PAGE
    @RequestMapping("")
    public String viewHomePage(){
        return "HomePage";
    }

//    LOGIN
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password ){
        List<User> user = repository.findByPhoneNumber(phoneNumber);
        if (CollectionUtils.isEmpty(user) || user.size() > 1) {
            return "login";
        }
        User u = user.get(0);
        if (u.getPassword().equals(password)) {
            if ("ADMIN".equalsIgnoreCase(u.getRole())) {
                return "adminHome";
            } else if ("CUSTOMER".equalsIgnoreCase(u.getRole())) {
                return "customerHome";
            } else {
                return "login";
            }
        } else {
            return "login";
        }
    }

//    SIGN-UP

    @RequestMapping(value = "/register")
    public String register(Model model){
        User register = new User();
        model.addAttribute("register", register);
        return "register";
    }

    @RequestMapping(value = "register/save")
    public String saveUpdate(@RequestParam(value = "id", required = false) Long id, @Valid User register, BindingResult result) {

        if (result.hasErrors()) {
            if (id == null) {
                return "register";
            } else {
                return "login";
            }
        }
        service.save(register);
        return "redirect:/login";
    }

}
