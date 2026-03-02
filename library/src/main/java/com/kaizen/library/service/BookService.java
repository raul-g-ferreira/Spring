package com.kaizen.library.service;

import com.kaizen.library.exception.BookNotFoundOrUnavailableException;
import com.kaizen.library.model.Book;
import com.kaizen.library.model.BookCode;
import com.kaizen.library.repository.BookCodeRepository;
import com.kaizen.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookCodeRepository bookCodeRepository;



    public BookService(BookRepository bookRepository, BookCodeRepository bookCodeRepository) {
        this.bookRepository = bookRepository;
        this.bookCodeRepository = bookCodeRepository;
    }

    public Book registerBook(Book book) {
        List<Book> allBooks = bookRepository.findAll();

        // Verifica se o livro já existe comparando por title, author e category
        Book existingBook = allBooks.stream()
                .filter(b -> b.equals(book))
                .findFirst()
                .orElse(null);

        if (existingBook == null) {
            return bookRepository.save(book);
        }// Adicionar excecao para livro ja existente
        return existingBook;
    }

    public Book addBook(Book book) {
        List<Book> allBooks = bookRepository.findAll();

        // Verifica se o livro já existe comparando por title, author e category
        Book existingBook = allBooks.stream()
                .filter(b -> b.equals(book))
                .findFirst()
                .orElse(null);

        if (existingBook != null) {
            // Se existe: aumenta o inStock
            increaseStock(existingBook);
            return bookRepository.save(existingBook);
        } else {
            // Se não existe: salva o novo livro
            increaseStock(book); // Define inStock como 1 para o novo livro
            return bookRepository.save(book);
        }
    }

    public void increaseStock(Book book) {
        book.setInStock(book.getInStock() + 1);
        bookRepository.save(book); // Salva o Book primeiro
        bookCodeRepository.save(new BookCode(book)); // Depois cria o BookCode
    }

    public void decreaseStock(Book book) {
        if (book.getInStock() > 0) {
            book.setInStock(book.getInStock() - 1);
            bookRepository.save(book); // Salva o Book primeiro
            bookCodeRepository.save(new BookCode(book)); // Depois cria o BookCode
        }else {
            throw new BookNotFoundOrUnavailableException();
        }
    }




    public void delete(Long id) {
        bookCodeRepository.findAll().stream().forEach(bookCode -> {;
            if (bookCode.getBook().getId().equals(id)) {
                bookCodeRepository.delete(bookCode);
            }
        });
        bookRepository.deleteById(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }
}
