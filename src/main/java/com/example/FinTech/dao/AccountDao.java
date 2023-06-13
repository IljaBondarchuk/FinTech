package com.example.FinTech.dao;

import com.example.FinTech.entity.Account;

import java.util.List;

public interface AccountDao {
    Account save(Account theAccount);
    List<Account> findAll();
    Account findById(int theId);
    void deleteById(int theId);


}
