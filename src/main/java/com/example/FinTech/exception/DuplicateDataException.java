package com.example.FinTech.exception;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String message){
        super(message);
    }
}
