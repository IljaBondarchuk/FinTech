package com.example.FinTech.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="Account")
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Long getIdentifier() {
        return identifier;
    }

    public Long getPassportNumber() {
        return passportNumber;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

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

    public Account(){
    }
    public Account(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account( String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
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
