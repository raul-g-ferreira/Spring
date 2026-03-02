package com.kaizen.library.service;

import com.kaizen.library.exception.BookNotFoundOrUnavailableException;
import com.kaizen.library.model.BookCode;
import com.kaizen.library.repository.BookCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCodeService {

    private final BookCodeRepository bookCodeRepository;

    public BookCodeService(BookCodeRepository bookCodeRepository) {
        this.bookCodeRepository = bookCodeRepository;
    }

    public List<BookCode> getAll() {
        return bookCodeRepository.findAll();
    }

    public BookCode findById(Long bookId) {
        List<BookCode> bookCodes = bookCodeRepository.findAll();

        return bookCodeRepository.findBookCodeByIdAndIsAvailable(bookId, true)
                .orElseThrow(() -> new BookNotFoundOrUnavailableException());
    }

    public void changeIsAvailable(BookCode bookCode) {
        bookCode.setIsAvailable(!bookCode.getIsAvailable());
        bookCodeRepository.save(bookCode);
    }
}
