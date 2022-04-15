package com.example.online_banking.controller;

import com.example.online_banking.model.Card;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.CardRepository;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private RegisterService service;

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping("/viewBalance/{id}")
    public String showViewBalance(@PathVariable(value = "id") Long id, Model model){
        Card card = cardRepository.getById(id);
        model.addAttribute("card", card);
        return "viewBalancePage";
    }


}
