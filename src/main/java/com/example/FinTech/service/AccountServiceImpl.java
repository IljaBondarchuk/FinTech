package com.example.FinTech.service;

import com.example.FinTech.dao.AccountDao;
import com.example.FinTech.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao theAccountDao){
        accountDao = theAccountDao;
    }
    @Transactional
    @Override
    public Account save(Account theAccount) {
        theAccount.setCreditBalance(BigDecimal.ZERO);
        theAccount.setAvailableBalance(BigDecimal.ZERO);
        return accountDao.save(theAccount);
    }

    @Transactional
    @Override
    public Account update(Account theAccount){
        Account account = accountDao.findById(theAccount.getId());
        theAccount.setAvailableBalance(account.getAvailableBalance());
        Account dbAccount = accountDao.save(theAccount);
        return dbAccount;
    }

    @Override
    public boolean checkPassportId(Account theAccount) {
        List<Account> accountList = accountDao.findAll();
        if (theAccount.getPassportNumber() != null){
            for (Account account:
                 accountList) {
                if (Objects.equals(account.getPassportNumber(), theAccount.getPassportNumber())){
                    throw new RuntimeException("Duplicate Passport Number");
                };
            }
        }
        return true;
    }

    @Override
    public boolean checkIdentifierNumber(Account theAccount) {
        List<Account> accountList = accountDao.findAll();
        if (theAccount.getPassportNumber() != null){
            for (Account account:
                    accountList) {
                if (Objects.equals(account.getIdentifier(), theAccount.getIdentifier())){
                    throw new RuntimeException("Duplicate Identifier Number");
                };
            }
        }
        return true;
    }

    @Transactional
    @Override
    public Account upCreditLimit(Long id, BigDecimal requestedCreditLimit) {
        Account account = accountDao.findById(id);
        if ((requestedCreditLimit.compareTo(account.getAnnualIncome().divide(BigDecimal.valueOf(12)))) > 0){
            System.out.println("You have low annual income. Increase your income and try again later");
            return account;
        }
        account.setCreditLimit(requestedCreditLimit);
        Account dbAccount = accountDao.save(account);
        return dbAccount;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(Long theId) {
        return accountDao.findById(theId);
    }

    @Transactional
    @Override
    public void deleteById(Long theId) {
     accountDao.deleteById(theId);
    }


}
