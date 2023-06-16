package com.example.FinTech.service;

import com.example.FinTech.entity.Account;

import java.util.List;

public interface AccountService {

    Account save(Account theAccount);
    List<Account> findAll();
    Account findById(Long theId);
    void deleteById(Long theId);
    Account update(Account theAccount);
    boolean checkPassportId (Account theAccount);
    boolean checkIdentifierNumber(Account theAccount);
    Account upCreditLimit(Long id, int requestedCreditLimit);
}
