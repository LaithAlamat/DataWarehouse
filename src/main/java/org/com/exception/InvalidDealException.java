package org.com.exception;

public class InvalidDealException extends RuntimeException {
    public InvalidDealException(String message) {
        super(message);
    }
}
