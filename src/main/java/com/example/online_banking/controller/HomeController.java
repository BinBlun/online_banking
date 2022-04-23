package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.model.UserRole;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.repository.UserRoleRepository;
import com.example.online_banking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RegisterService service;

    @Autowired
    private UserRoleRepository userRoleRepository;
    // HOME PAGE
    @RequestMapping("/home")
    public String viewHomePage(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = repository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "HomePage";
    }

    @RequestMapping("/403")
    public String errorPage() {
        return "errorPage";
    }

    //    LOGIN
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    // logout
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password ){
        List<User> user = repository.findByPhoneNumber(phoneNumber);
        if (CollectionUtils.isEmpty(user) || user.size() > 1) {
            return "redirect:/login";
        }
        User u = user.get(0);

        if (u.getPassword().equals(password)) {
            if ("ADMIN".equalsIgnoreCase(u.getRole())) {
                return "redirect:/admin";
            } else if ("CUSTOMER".equalsIgnoreCase(u.getRole())) {
                Long accountID = u.getId();
                return "redirect:/customer";
            } else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/login";
        }
    }


//    SIGN-UP

    @RequestMapping(value = "/register")
    public String register(Model model) {
        User register = new User();

        model.addAttribute("register", register);
        return "register";
    }

    @RequestMapping(value = "register/save")
    public String saveUpdate(@RequestParam(value = "id", required = false) Long id, @Valid User register, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
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
