package org.com.exception;

public class DuplicateDealException extends RuntimeException {
    public DuplicateDealException(String message) {
        super(message);
    }
}
