package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RegisterService service;

//    @RequestMapping(value = "")
//    public String register(Model model){
//        User register = new User();
////        Register existsUser = registerRepository.getByEmail
//        model.addAttribute("a", register);
//        return "register";
//    }
//
//    @RequestMapping(value = "/save")
//    public String saveUpdate(@RequestParam(value = "id", required = false) Long id, @Valid User register, BindingResult result) {
//
//        if (result.hasErrors()) {
//            if (id == null) {
//                return "register";
//            } else {
//                /*return "companyUpdate";*/
//            }
//        }
//        service.save(register);
//        return "redirect:/register";
//    }
}
