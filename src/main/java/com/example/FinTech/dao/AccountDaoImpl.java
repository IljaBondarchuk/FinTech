package com.example.FinTech.dao;

import com.example.FinTech.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    private EntityManager entityManager;

    @Autowired
    public AccountDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }



    @Override
    public List<Account> findAll() {
        TypedQuery<Account> theQuery = entityManager.createQuery("from Account", Account.class);
        List<Account> accounts = theQuery.getResultList();
        return accounts;
    }

    @Override
    public Account findById(Long theId) {
        Account theAccount = entityManager.find(Account.class,theId);
        return theAccount;
    }

    @Override
    public Account save(Account theAccount) {
        Account dbAccount = entityManager.merge(theAccount);
        return dbAccount;
    }

    @Override
    public void deleteById(Long theId) {
        Account theAccount = entityManager.find(Account.class, theId);
        entityManager.remove(theAccount);
    }

    @Override
    public Account update(Account theAccount) {
        Account account = new Account();
        
        return null;
    }
}
