package com.example.FinTech.rest;

import com.example.FinTech.entity.Account;
import com.example.FinTech.service.AccountService;
import org.springframework.web.bind.annotation.*;

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
    public Account findById(@PathVariable int accountId){
        Account theAccount = accountService.findById(accountId);

        if (theAccount == null){
            throw new RuntimeException("Account id not found - " + accountId);
        }
        return theAccount;
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestBody Account theAccount){
        theAccount.setId(0L);
        Account dbAccount = accountService.save(theAccount);
        return dbAccount;
    }

}
