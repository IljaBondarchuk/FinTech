package com.example.FinTech.integration;

import org.testcontainers.containers.MySQLContainer;

public class BaeldungMySQLContainer extends MySQLContainer<BaeldungMySQLContainer> {
    private static final String IMAGE_VERSION = "mysql:latest";
    private static BaeldungMySQLContainer container;

    private BaeldungMySQLContainer(){
        super(IMAGE_VERSION);
    }

    public static BaeldungMySQLContainer getInstance(){
        if (container == null){
            container = new BaeldungMySQLContainer();
        }
        return container;
    }
    @Override
    public void start(){
        super.start();
        System.setProperty("DB_URL",container.getJdbcUrl());
        System.setProperty("DB_USERNAME",container.getUsername());
        System.setProperty("DB_PASSWORD",container.getPassword());
    }

    @Override
    public void stop(){

    }

}
