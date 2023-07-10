package com.example.FinTech.rest;

import com.example.FinTech.entity.Account;
import com.example.FinTech.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(AccountService theAccountService){
        accountService = theAccountService;
    }

    @GetMapping("/accounts")
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("accounts/{accountId}")
    public Account findById(@PathVariable Long accountId){
        Account theAccount = accountService.findById(accountId);

        if (theAccount == null){
            throw new RuntimeException("Account id not found - " + accountId);
        }
        return theAccount;
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestBody Account theAccount){
        if (accountService.checkPassportId(theAccount) || accountService.checkIdentifierNumber(theAccount)){
            Account dbAccount = accountService.save(theAccount);
            return dbAccount;
        } else {
            System.out.println("Duplicating values");
            return null;
        }
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody Account theAccount){
        Account dbAccount = accountService.update(theAccount);
        return dbAccount;
    }

    @PutMapping("/accounts/{accountId}")
    public Account updateCreditLimit(@PathVariable Long accountId, @RequestBody Account account){
        Account dbAccount = accountService.upCreditLimit(accountId, account.getCreditLimit());
        return dbAccount;
    }

}
