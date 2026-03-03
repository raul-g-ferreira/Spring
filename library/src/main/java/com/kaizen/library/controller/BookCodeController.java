package com.kaizen.library.controller;

import com.kaizen.library.service.BookCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-code")
public class BookCodeController {

    @Autowired
    private BookCodeService bookCodeService;

    @GetMapping
    public Object getAll() {
        return bookCodeService.getAll();
    }
}
