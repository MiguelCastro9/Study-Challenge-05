package com.casumo.videorental.exception;

/**
 *
 * @author Miguel Castro
 */
public class MovieNotFoundException extends RuntimeException {
    
    public MovieNotFoundException(String message) {
        super(message);
    }
}
