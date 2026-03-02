package com.kaizen.library.infra;

import com.kaizen.library.exception.BookNotFoundOrUnavailableException;
import com.kaizen.library.exception.ClientNotFoundException;
import com.kaizen.library.exception.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    private RestErrorMessage handleClientNotFoundException(ClientNotFoundException exception){
        return new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(BookNotFoundOrUnavailableException.class)
    private RestErrorMessage handleBookNotFoundException(BookNotFoundOrUnavailableException exception){
        return new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    private RestErrorMessage handleLoanNotFoundException(LoanNotFoundException exception){
        return new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
