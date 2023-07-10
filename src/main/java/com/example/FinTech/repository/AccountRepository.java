package com.example.FinTech.repository;

import com.example.FinTech.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "Select identifier from account",
            nativeQuery = true
    )
    Set<Long> getIdentifiers();

    @Query(value = "Select passport_number from account",
            nativeQuery = true
    )
    Set<Long> getPassports();
}
