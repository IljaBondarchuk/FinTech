package com.example.FinTech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Account")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;



    @Column(name = "identifier")
    private Long identifier;
    @Column(name = "passport_number")
    private Long passportNumber;
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
    @Column(name = "available_balance")
    private BigDecimal availableBalance;
    @Column(name = "credit_balance")
    private BigDecimal creditBalance;
    @Column(name = "deposit_balance")
    private BigDecimal depositBalance;
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    public Account(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account( String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(String firstName, String lastName, Long identifier, Long passportNumber, BigDecimal annualIncome, BigDecimal depositBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifier = identifier;
        this.passportNumber = passportNumber;
        this.annualIncome = annualIncome;
        this.availableBalance = BigDecimal.valueOf(0);
        this.creditBalance = BigDecimal.valueOf(0);
        this.depositBalance = depositBalance;
        this.creditLimit = BigDecimal.valueOf(0);
    }

    public Account(String firstName, String lastName, Long identifier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifier = identifier;
        this.availableBalance = BigDecimal.valueOf(0);
        this.creditBalance = BigDecimal.valueOf(0);
        this.creditLimit = BigDecimal.valueOf(0);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identifier=" + identifier +
                ", passportNumber=" + passportNumber +
                ", annualIncome=" + annualIncome +
                ", availableBalance=" + availableBalance +
                ", creditBalance=" + creditBalance +
                ", depositBalance=" + depositBalance +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
