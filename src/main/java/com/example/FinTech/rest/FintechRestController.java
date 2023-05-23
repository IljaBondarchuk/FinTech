package com.example.FinTech.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FintechRestController {
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
}
