package com.kaizen.library.controller;


import com.kaizen.library.dto.LoanDTO;
import com.kaizen.library.model.Loan;
import com.kaizen.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {

        this.loanService = loanService;
    }


    @GetMapping
    public List<Loan> getAllLoans() {
        return this.loanService.getAllLoans();
    }

    @PostMapping("/new")
    public ResponseEntity<Loan> newLoan(@RequestBody LoanDTO loanDto) {
        return ResponseEntity.ok(this.loanService.newLoan(loanDto));
    }
    
    @PostMapping("/return/{loanId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long loanId) {
        this.loanService.returnBook(loanId);
        return ResponseEntity.ok().build();
    }
    
        
}
