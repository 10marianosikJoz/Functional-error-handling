package com.example.exception_handling.application.exception;

public class CriminalReportUnexpectedException extends RuntimeException {

    public CriminalReportUnexpectedException(Throwable message) {
        super(message);
    }
}
