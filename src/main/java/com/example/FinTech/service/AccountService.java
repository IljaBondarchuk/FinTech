package com.example.FinTech.service;

import com.example.FinTech.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account save(Account theAccount);
    List<Account> findAll();
    Account findById(Long theId);
    void deleteById(Long theId);
    Account update(Account theAccount);
    Account upCreditLimit(Long id, BigDecimal requestedCreditLimit);
}
