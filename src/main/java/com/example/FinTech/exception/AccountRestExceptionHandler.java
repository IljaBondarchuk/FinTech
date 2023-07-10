package com.example.FinTech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException (AccountNotFoundException exc){
        AccountErrorResponse errorResponse = new AccountErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    //another exception handler to catch any exception
    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(Exception exc){
        AccountErrorResponse errorResponse = new AccountErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
