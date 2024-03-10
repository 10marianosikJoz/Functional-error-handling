package com.example.exception_handling.application.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(Throwable message) {
        super(message);
    }
}
