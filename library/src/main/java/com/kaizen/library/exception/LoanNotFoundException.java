package com.kaizen.library.exception;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException() {super("Loan not found");}

    public LoanNotFoundException(String message) {
        super(message);
    }
}
