package com.example.FinTech.service;

import com.example.FinTech.entity.Account;
import com.example.FinTech.exception.AccountNotFoundException;
import com.example.FinTech.exception.DuplicateDataException;
import com.example.FinTech.repository.AccountRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness =  Strictness.LENIENT)
public class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    public void setup(){
        account = Account.builder()
                .id(0L)
                .firstName("Serious")
                .lastName("Sam")
                .identifier(11111111L)
                .passportNumber(222222L)
                .annualIncome(new BigDecimal(10000))
                .creditLimit(new BigDecimal(0))
                .depositBalance(new BigDecimal(1000))
                .availableBalance(new BigDecimal(0))
                .creditBalance(new BigDecimal(100))
                .build();
    }

    @DisplayName("JUnit test for saveAccount method")
    @Test
    public void givenAccountObject_whenSaveAccount_thenReturnAccountObject(){
        //given precondition or setup
        given(accountRepository.findById(account.getId()))
             .willReturn(Optional.empty());
        given(accountRepository.save(account)).willReturn(account);
        System.out.println(accountRepository);
        System.out.println(accountService);

        //when - action or the behaviour that we are going testt
        Account savedAccount = accountService.save(account);
        System.out.println(savedAccount);

        //then verify the output
        assertThat(savedAccount).isNotNull();
    }

    @DisplayName("JUnit test for saveAccount method which throws PassportId exception")
    @Test
    public void givenAccountObject_whenSaveAccount_thenThrowsPassportIdException(){
        //given precondition or setup

        given(accountRepository.existsByPassportNumber(account.getPassportNumber())).willReturn(true);

        System.out.println(accountRepository);
        System.out.println(accountService);

        //when - action or the behaviour that we are going test
        assertThrows(DuplicateDataException.class, () -> {
            accountService.save(account);
        });

        //then verify the output
        verify(accountRepository, never()).save(any(Account.class));
    }

    @DisplayName("JUnit test for saveAccount method which throws Identifier exception")
    @Test
    public void givenAccountObject_whenSaveAccount_thenThrowsIdentifierException(){
        //given precondition or setup

        given(accountRepository.existsByPassportNumber(account.getPassportNumber())).willReturn(false);
        given(accountRepository.existsByIdentifier(account.getIdentifier())).willReturn(true);

        System.out.println(accountRepository);
        System.out.println(accountService);

        //when - action or the behaviour that we are going test
        assertThrows(DuplicateDataException.class, () -> {
            accountService.save(account);
        });

        //then verify the output
        verify(accountRepository, never()).save(any(Account.class));
    }
@DisplayName("JUnit test for saveAccount method that Credit and Available balances are zero ")
@Test
public void givenAccountObject_whenSaveAccount_thenReturnAccountObjectWithCreditAndAvailableBalanceZero(){
    //given precondition or setup
    given(accountRepository.findById(account.getId()))
            .willReturn(Optional.empty());
    given(accountRepository.save(account)).willReturn(account);

    //when - action or the behaviour that we are going testt
    Account savedAccount = accountService.save(account);

    //then verify the output
    assertThat(savedAccount.getAvailableBalance()).isZero();
    assertThat(savedAccount.getCreditBalance()).isZero();
}



  //  @Disabled
    @DisplayName("Junit test for update method should return the same available balance and changes another value")
    @Test
    public void givenAccountObject_whenUpdateAccount_thenReturnAccountWithTheSameAvailableBalance(){
        Account newAccount = Account.builder()
                .id(0L)
                .firstName("Serious")
                .lastName("Sam")
                .identifier(11111111L)
                .passportNumber(222222L)
                .annualIncome(new BigDecimal(999))
                .creditLimit(new BigDecimal(0))
                .depositBalance(new BigDecimal(1000))
                .availableBalance(new BigDecimal(10000))
                .creditBalance(new BigDecimal(100))
                .build();

    given(accountRepository.findById(newAccount.getId())).willReturn(Optional.of(account));
    given(accountRepository.save(newAccount)).willReturn(newAccount);

   Account updatedAccount = accountService.update(newAccount);


   // assertThat(updatedAccount).isNotNull();
  assertThat(updatedAccount.getAvailableBalance()).isEqualTo(new BigDecimal(0));
  assertThat(updatedAccount.getAnnualIncome()).isEqualTo(new BigDecimal(999));
}
    @DisplayName("JUnit test for delete Account and should return Id of deleting account ")
    @Test
    public void givenAccountObject_whenDeleteAccount_thenReturnIdOfDeletedAccount(){
        //given precondition or setup
        given(accountRepository.findById(account.getId()))
                .willReturn(Optional.ofNullable(account));

        //when - action or the behaviour that we are going test
        accountService.deleteById(account.getId());

        //then verify the output
        verify(accountRepository,times(1)).deleteById(account.getId());
    }

    @DisplayName("JUnit test for findAll Accounts and should return list of accounts")
    @Test
    public void givenListOfAccountObjects_whenFindAllAccount_thenReturnListOfAccounts(){
        //given precondition or setup
        Account account1 = Account.builder()
                .id(1L)
                .firstName("Abcd")
                .lastName("Dcba")
                .identifier(11111133L)
                .passportNumber(222211L)
                .annualIncome(new BigDecimal(10000))
                .creditLimit(new BigDecimal(0))
                .depositBalance(new BigDecimal(1000))
                .availableBalance(new BigDecimal(0))
                .creditBalance(new BigDecimal(100))
                .build();

        Account account2 = Account.builder()
                .id(2L)
                .firstName("Qwerty")
                .lastName("Asdfg")
                .identifier(11111134L)
                .passportNumber(222212L)
                .annualIncome(new BigDecimal(10000))
                .creditLimit(new BigDecimal(0))
                .depositBalance(new BigDecimal(1000))
                .availableBalance(new BigDecimal(0))
                .creditBalance(new BigDecimal(100))
                .build();

        given(accountRepository.findAll())
                .willReturn(List.of(account,account1,account2));

        //when - action or the behaviour that we are going test
        List <Account> accountList = accountService.findAll();

        //then verify the output
        assertThat(accountList).isNotNull();
        assertThat(accountList.size()).isEqualTo(3);
    }
}
