package com.kaizen.library.controller;

import com.kaizen.library.model.Book;
import com.kaizen.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/register")
    public Book register(@RequestBody Book book) {
        return bookService.registerBook(book);
    }

    @PostMapping("/add")
    public Book add(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }


}
