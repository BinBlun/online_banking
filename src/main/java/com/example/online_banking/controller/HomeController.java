//package com.example.online_banking.controller;
//
//import com.example.online_banking.model.Users;
//import com.example.online_banking.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Collections;
//import java.util.List;
//
//@Controller
//@RequestMapping("/")
//public class HomeController {
//
//    @Autowired
//    private UsersRepository repository;
//
//    @PostMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/doLogin")
//    public String doLogin(@RequestBody Users usersLogin) {
//        List<Users> users = repository.findByUserName(usersLogin.getUserName());
//        if (CollectionUtils.isEmpty(users) || users.size() > 1) {
//            return "login";
//        }
//        Users u = users.get(0);
//        if (u.getPassword().equals(usersLogin.getPassword())) {
//            if ("ADMIN".equalsIgnoreCase(u.getRole())) {
//                return "adminHome";
//            } else if ("CUSTOMER".equalsIgnoreCase(u.getRole())) {
//                return "customerHome";
//            } else {
//                return "login";
//            }
//        } else {
//            return "login";
//        }
//    }
//}
