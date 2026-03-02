package com.kaizen.library.service;

import com.kaizen.library.dto.LoanDTO;
import com.kaizen.library.exception.LoanNotFoundException;
import com.kaizen.library.model.Book;
import com.kaizen.library.model.BookCode;
import com.kaizen.library.model.Client;
import com.kaizen.library.model.Loan;
import com.kaizen.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    private ClientService clientService;
    @Autowired
    private BookCodeService bookCodeService;
    @Autowired
    private BookService bookService;
    @Autowired
    private EmailService emailService;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan newLoan(LoanDTO loanDto) {
        Client client = clientService.findById(loanDto.getClientId());
        BookCode bookCode = bookCodeService.findById(loanDto.getBookCodeId());
        Loan loan = new Loan(client, bookCode);

        Book book = this.bookService.findById(bookCode.getBook().getId());

        bookService.decreaseStock(book);
        bookCodeService.changeIsAvailable(bookCode);

        emailService.sendEmailLoan(client.getEmail(), book.getTitle());

        return loanRepository.save(loan);
    }

    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException());
        BookCode bookCode = loan.getBookCode();
        Book book = bookCode.getBook();

        bookService.increaseStock(book);
        bookCodeService.changeIsAvailable(bookCode);
        loanRepository.delete(loan);
        emailService.sendEmailReturn(loan.getClient().getEmail(), book.getTitle());
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
