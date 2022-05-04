package com.example.online_banking.controller.rest;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.model.LoansPackage;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.repository.UserRoleRepository;
import com.example.online_banking.rest.model.ErrorCode;
import com.example.online_banking.rest.model.Page;
import com.example.online_banking.rest.model.PagingRequest;
import com.example.online_banking.rest.model.ResponseData;
import com.example.online_banking.service.UserService;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/rest")
public class RestAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository roleRepository;

    // list customer
    @PostMapping("/get-customer-list")
    public Page<User> getCustomerList(@RequestBody PagingRequest pagingRequest) {
        return userService.getCustomerList(pagingRequest);
    }

    @PostMapping("/delete-user")
    public ResponseData deleteUser(@RequestParam("id") Long id)
            throws DataInvalidException {
        return ResponseData.ok(userService.deleteUser(id));
    }

    //TODO: list admin
    @PostMapping("/get-admin-list")
    public Page<User> getAdminList(@RequestBody PagingRequest pagingRequest) {
        return userService.getAdminList(pagingRequest);
    }

//    // edit admin
//    @GetMapping("/edit-user/{id}")
//    public Page<User> editAdmin(@RequestBody PagingRequest pagingRequest) {
//        return userService.editAdmin(pagingRequest);
//    }

    // TODO: manage loans package
    @PostMapping("/get-loans-package-list")
    public Page<LoansPackage> getLoansPackageList(@RequestBody PagingRequest pagingRequest) {
        return userService.getLoansPackageList(pagingRequest);
    }
}
