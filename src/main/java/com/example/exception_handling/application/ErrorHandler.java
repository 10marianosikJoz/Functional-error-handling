package com.example.exception_handling.application;

import com.example.exception_handling.application.exception.CriminalReportUnexpectedException;
import com.example.exception_handling.application.exception.DatabaseException;
import com.example.exception_handling.application.exception.PersonNotFoundException;
import com.example.exception_handling.application.exception.ReportNotFoundForPersonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiServiceErrorResponse personNotFoundException(PersonNotFoundException exception) {
        return new ApiServiceErrorResponse(404, "Person with given surname does not exists");
    }

    @ExceptionHandler(ReportNotFoundForPersonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiServiceErrorResponse reportNotFoundForUserException() {
        return new ApiServiceErrorResponse(404, "Criminal report for person not found");
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiServiceErrorResponse databaseException() {
        return new ApiServiceErrorResponse(500, "Database exception");
    }

    @ExceptionHandler(CriminalReportUnexpectedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiServiceErrorResponse criminalReportServiceUnexpectedException() {
        return new ApiServiceErrorResponse(500, "Criminal report service unexpected exception");
    }
}
