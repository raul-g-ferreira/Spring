package com.kaizen.library.controller;


import com.kaizen.library.dto.LoanDTO;
import com.kaizen.library.model.Loan;
import com.kaizen.library.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @GetMapping
    public List<Loan> getAllLoans() {
        logger.info("GET /api/loan - Getting all loans");
        return this.loanService.getAllLoans();
    }

    @PostMapping("/new")
    public ResponseEntity<Loan> newLoan(@RequestBody LoanDTO loanDto) {
        logger.info("POST /api/loan/new - Creating new loan with bookCodeId: {}, clientId: {}", loanDto.getBookCodeId(), loanDto.getClientId());
        return ResponseEntity.ok(this.loanService.newLoan(loanDto));
    }

    @PostMapping("/web/loan")
    public ResponseEntity<Loan> newLoanWeb(@RequestBody LoanDTO loanDto) {
        logger.info("POST /api/loan/web/loan - Creating new loan with bookId: {}, clientId: {}", loanDto.getBookCodeId(), loanDto.getClientId());
        return ResponseEntity.ok(this.loanService.newWebLoan(loanDto));
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long loanId) {
        logger.info("POST /api/loan/return/{} - Returning book", loanId);
        this.loanService.returnBook(loanId);
        return ResponseEntity.ok().build();
    }


}
