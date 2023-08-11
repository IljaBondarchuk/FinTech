package com.example.FinTech.rest;

import com.example.FinTech.entity.Account;
import com.example.FinTech.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    public Account account;

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
    @Test
    public void givenAccountObject_whenPostCreateAccount_thenReturnSavedAccount() throws Exception{

        //given
        BDDMockito.given(accountService.save(ArgumentMatchers.any((Account.class)))).willAnswer((invocation) -> invocation.getArgument(0));
        //when
        ResultActions response = mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)));
        //then
        response.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(account.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(account.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identifier").value(account.getIdentifier()));
    }

}
