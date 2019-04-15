package com.example.helloserviceapi.controller;

import com.example.helloserviceapi.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
