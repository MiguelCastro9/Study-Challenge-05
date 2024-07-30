package com.casumo.videorental.exception;

/**
 *
 * @author Miguel Castro
 */
public class InvalidRentalDaysException extends RuntimeException {
    
    public InvalidRentalDaysException(String message) {
        super(message);
    }
}