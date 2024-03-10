package com.example.exception_handling.application.exception;

public class ReportNotFoundForPersonException extends RuntimeException {

    public ReportNotFoundForPersonException(Throwable message) {
        super(message);
    }
}
