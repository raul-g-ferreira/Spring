package com.kaizen.library.controller;

import com.kaizen.library.model.Book;
import com.kaizen.library.service.BookService;
import com.kaizen.library.service.GoogleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private GoogleBookService googleBookService;
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

    @PostMapping("/gbook/{isbn}")
    public Book addGBook(@PathVariable String isbn) {
        return bookService.addBook(googleBookService.createBookByIsbn(isbn));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
