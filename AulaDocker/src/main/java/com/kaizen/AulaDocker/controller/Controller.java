package com.kaizen.AulaDocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/docker")
    public String mensagem(){
        return "Olá, seja bem-vindo ao curso de Docker!";
    }
}
