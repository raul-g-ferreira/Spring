package com.kaizen.library.exception;

public class BookNotFoundOrUnavailableException extends RuntimeException {

    public BookNotFoundOrUnavailableException() {super("Book is out of stock");}

    public BookNotFoundOrUnavailableException(String message) {
        super(message);
    }
}
