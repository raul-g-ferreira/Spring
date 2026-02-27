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

    @PostMapping
    public Book create(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        bookService.delete(id);
    }
}
