package com.casumo.videorental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Miguel Castro
 */
@RestControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<MessageException> handleMovieNotFoundException(MovieNotFoundException ex) {
        MessageException message = new MessageException(ex.getMessage(), "Check if the movie ID is correct.");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRentalDaysException.class)
    public ResponseEntity<MessageException> handleInvalidRentalDaysException(InvalidRentalDaysException ex) {
        MessageException message = new MessageException(ex.getMessage(), "Rental days must be a positive integer.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageException> handleGeneralException(Exception ex) {
        MessageException message = new MessageException("An unexpected error occurred.", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
