package com.kipper.first_spring_app.controller;


import com.kipper.first_spring_app.domain.User;
import com.kipper.first_spring_app.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;


    @GetMapping
    public String hello() {
        return helloService.hello("nome foda");
    }

    @PostMapping("/{id}")
    public String helloPost(@PathVariable String id, @RequestParam(value = "filter", defaultValue = "nenhum")  String filter,  @RequestBody User body) {
        return "hello " + body.getName() + " " + id + filter;
    }
}
