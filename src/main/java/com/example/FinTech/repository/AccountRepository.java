package com.example.FinTech.repository;

import com.example.FinTech.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByPassportNumber(Long passportNumber);
    boolean existsByIdentifier(Long identifier);
}
