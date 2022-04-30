package com.example.online_banking.service;

import com.example.online_banking.model.LoansPackage;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.custom.UserRepositoryCustom;
import com.example.online_banking.rest.model.Page;
import com.example.online_banking.rest.model.PagingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    // manage customer
    public Page<User> getCustomerList(PagingRequest pagingRequest) {
        Integer total = userRepositoryCustom.getTotalUser(pagingRequest);
        List<User> userList = userRepositoryCustom.getUserList(pagingRequest);
        Page<User> page = new Page<>(userList);
        page.setRecordsFiltered(userList.size());
        page.setRecordsTotal(total);
        page.setDraw(pagingRequest.getDraw());
        return page;
    }

    //TODO: manage admin
    public Page<User> getAdminList(PagingRequest pagingRequest) {
        Integer total = userRepositoryCustom.getTotalAdmin(pagingRequest);
        List<User> adminList = userRepositoryCustom.getAdminList(pagingRequest);
        Page<User> page = new Page<>(adminList);
        page.setRecordsFiltered(adminList.size());
        page.setRecordsTotal(total);
        page.setDraw(pagingRequest.getDraw());
        return page;
    }

//    public Page<User> getAdminById(PagingRequest pagingRequest) {
//        return userRepositoryCustom.
//    }

    // TODO: manage loans package
    public Page<LoansPackage> getLoansPackageList(PagingRequest pagingRequest) {
        Integer total = userRepositoryCustom.getTotalLoansPackage(pagingRequest);
        List<LoansPackage> loansPackageList = userRepositoryCustom.getLoansPackageList(pagingRequest);
        Page<LoansPackage> page = new Page<>(loansPackageList);
        page.setRecordsFiltered(loansPackageList.size());
        page.setRecordsTotal(total);
        page.setDraw(pagingRequest.getDraw());
        return page;
    }
}
