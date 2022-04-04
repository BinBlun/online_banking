package com.example.online_banking.controller;

import com.example.online_banking.model.Register;
import com.example.online_banking.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    RegisterRepository registerRepository;

    @RequestMapping(value = "")
    public String register(Model model){
        Register register = new Register();
//        Register existsUser = registerRepository.getByEmail
        model.addAttribute("register", register);
        return "register";
    }

    @RequestMapping(value = "/save")
    public String saveUpdate(@RequestParam(value = "id", required = false) Long id, @Valid Register register, BindingResult result) {

        if (result.hasErrors()) {
            if (id == null) {
                return "register";
            } else {
                /*return "companyUpdate";*/
            }
        }
        register.setId(id);
        registerRepository.save(register);
        return "redirect:/register";
    }
}
