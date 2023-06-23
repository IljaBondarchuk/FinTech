package com.example.FinTech.rest;

import com.example.FinTech.dao.AccountDao;
import com.example.FinTech.entity.Account;
import com.example.FinTech.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;

class AccountServiceImplTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    void save_shouldSetBalancesToZeroAndReturnSavedAccount() {
        // Arrange
        Account account = new Account();
        account.setId(1L);

        when(accountDao.save(account)).thenReturn(account);

        // Act
        Account savedAccount = accountService.save(account);

        // Assert
        assertEquals(BigDecimal.ZERO, savedAccount.getCreditBalance());
        assertEquals(BigDecimal.ZERO, savedAccount.getAvailableBalance());
        verify(accountDao, times(1)).save(account);
    }

    @Test
    void update_shouldUpdateAccountAndReturnUpdatedAccount() {
        // Arrange
        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setAvailableBalance(BigDecimal.TEN);

        Account updatedAccount = new Account();
        updatedAccount.setId(1L);
        updatedAccount.setAvailableBalance(BigDecimal.TEN);

        when(accountDao.findById(1L)).thenReturn(existingAccount);
        when(accountDao.save(updatedAccount)).thenReturn(updatedAccount);

        // Act
        Account result = accountService.update(updatedAccount);

        // Assert
        assertEquals(existingAccount.getAvailableBalance(), result.getAvailableBalance());
        verify(accountDao, times(1)).findById(1L);
        verify(accountDao, times(1)).save(updatedAccount);
    }

    @Test
    void checkPassportId_shouldThrowExceptionIfDuplicatePassportNumberFound() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setPassportNumber(33333L);

        List<Account> accountList = new ArrayList<>();
        Account existingAccount = new Account();
        existingAccount.setId(2L);
        existingAccount.setPassportNumber(33333L);
        accountList.add(existingAccount);

        when(accountDao.findAll()).thenReturn(accountList);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> accountService.checkPassportId(account));
        verify(accountDao, times(1)).findAll();
    }


}





