package com.example.FinTech.service;

import com.example.FinTech.dao.AccountDao;
import com.example.FinTech.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao theAccountDao){
        accountDao = theAccountDao;
    }
    @Transactional
    @Override
    public Account save(Account theAccount) {
        return accountDao.save(theAccount);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(int theId) {
        return accountDao.findById(theId);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
     accountDao.deleteById(theId);
    }
}
