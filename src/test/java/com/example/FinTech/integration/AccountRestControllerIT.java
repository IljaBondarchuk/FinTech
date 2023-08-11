package com.example.FinTech.integration;

import com.example.FinTech.entity.Account;
import com.example.FinTech.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class AccountRestControllerIT {

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initialazer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
        public void initialize(ConfigurableApplicationContext configurableApplicationContext){
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username="+ mySQLContainer.getUsername(),
                    "spring.datasource.passwords="+  mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Account account;
    @BeforeEach
    void setup(){
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

        accountRepository.deleteAll();
    }

    @Test
    public void givenAccountObject_whenPostCreateAccount_thenReturnSavedAccount() throws Exception{

        //given

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
