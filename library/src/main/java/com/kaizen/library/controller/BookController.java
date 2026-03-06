package com.kaizen.library.controller;

import com.kaizen.library.model.Book;
import com.kaizen.library.service.BookService;
import com.kaizen.library.service.GoogleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private GoogleBookService googleBookService;
    @Autowired
    private BookService bookService;

    @GetMapping("/get")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/register")
    public Book register(@RequestBody Book book) {
        return bookService.registerBook(book);
    }

    @PostMapping("/add")
    public Book add(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/add/{quantity}")
    public Book add(@RequestBody Book book, @PathVariable Integer quantity) {
        for (Integer i = 1; i.compareTo(quantity) < 0; i++) {
            bookService.addBook(book);
        }
        return bookService.addBook(book);
    }

    @PostMapping("/import/{isbn}")
    public Book importBook(@PathVariable String isbn) {
        return bookService.addBook(googleBookService.createBookByIsbn(isbn));
    }

    @PutMapping("/update/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
