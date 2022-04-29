package com.example.online_banking.controller.rest;

import com.example.online_banking.model.User;
import com.example.online_banking.rest.model.Page;
import com.example.online_banking.rest.model.PagingRequest;
import com.example.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest")
public class RestAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/get-customer-list")
    public Page<User> getCustomerList(@RequestBody PagingRequest pagingRequest) {
        return userService.getCustomerList(pagingRequest);
    }
}
