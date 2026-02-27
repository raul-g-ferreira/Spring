package com.kipper.first_spring_app.service;


import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello(String name) {
        return "hello" + name;
    }
}
