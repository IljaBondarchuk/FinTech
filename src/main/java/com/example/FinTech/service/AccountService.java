package com.example.FinTech.service;

import com.example.FinTech.entity.Account;

import java.util.List;

public interface AccountService {

    Account save(Account theAccount);
    List<Account> findAll();
    Account findById(int theId);
    void deleteById(int theId);
}
