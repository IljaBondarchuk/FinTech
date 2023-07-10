package com.example.FinTech.service;

import com.example.FinTech.exception.AccountNotFoundException;
import com.example.FinTech.repository.AccountRepository;
import com.example.FinTech.entity.Account;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Account save(Account theAccount) {
        theAccount.setCreditBalance(BigDecimal.ZERO);
        theAccount.setAvailableBalance(BigDecimal.ZERO);
        return accountRepository.save(theAccount);
    }

    @Transactional
    @Override
    public Account update(Account theAccount){
        Account account = accountRepository.findById(theAccount.getId()).orElseThrow(()-> new AccountNotFoundException("Account not found " + theAccount.getId()));
        theAccount.setAvailableBalance(account.getAvailableBalance());
        Account dbAccount = accountRepository.save(theAccount);
        return dbAccount;
    }

    @Override
    public boolean checkPassportId(Account theAccount) {
        Set<Long> uniquePassports = accountRepository.getPassports();
        if (theAccount.getPassportNumber() != null){
            return  !uniquePassports.contains(theAccount.getPassportNumber());
        }
        return false;
    }

    @Override
    public boolean checkIdentifierNumber(Account theAccount) {
        Set<Long> uniqueIdentifiers = accountRepository.getIdentifiers();
        if (theAccount.getIdentifier() != null){
        return  !uniqueIdentifiers.contains(theAccount.getIdentifier());
        }
        return false;
    }

    @Transactional
    @Override
    public Account upCreditLimit(Long id, BigDecimal requestedCreditLimit) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Account not found " + id));
        if ((requestedCreditLimit.compareTo(account.getAnnualIncome().divide(BigDecimal.valueOf(12),2, RoundingMode.HALF_UP))) > 0){
            System.out.println("You have low annual income. Increase your income and try again later");
            return account;
        }
        account.setCreditLimit(requestedCreditLimit);
        Account dbAccount = accountRepository.save(account);
        return dbAccount;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long theId) {
        return accountRepository.findById(theId).orElseThrow(()-> new AccountNotFoundException("Account not found " + theId));
    }

    @Transactional
    @Override
    public void deleteById(Long theId) {
        accountRepository.deleteById(theId);
    }


}
