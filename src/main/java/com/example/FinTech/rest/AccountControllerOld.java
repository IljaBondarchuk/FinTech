package com.example.FinTech.rest;

import com.example.FinTech.entity.Account;
import com.example.FinTech.exception.AccountNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api_Old")
public class AccountControllerOld {

   private List<Account> theAccounts;
    //Home page
    @GetMapping("/")
    public String welcome(){
        return "<html><body>"
                + "<h1>WELCOME TO ACCOUNTS CONTROLLER</h1>"
                + "</body></html>";
    }
    @PostConstruct
    public void loadData(){
       theAccounts = new ArrayList<>();

        theAccounts.add(new Account(0L,"Illia","Bondarchuk"));
        theAccounts.add(new Account(1L,"Jeanna","Bondarchuk"));
        theAccounts.add(new Account(2L,"John","Doe"));
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return theAccounts;
    }

    @GetMapping("/accounts/{accountId}")
    public Account findById(@PathVariable int accountId){
        if (theAccounts.size()  <= accountId || accountId < 0){
            throw new AccountNotFoundException("Account id not found - " + accountId);
        }

        return theAccounts.get(accountId);
    }


}
