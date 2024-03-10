package com.example.exception_handling.application;

public sealed interface ServiceError extends GeneralError {

    record CriminalReportServiceError(String message) implements ServiceError {}
    record DatabaseError(Throwable message) implements ServiceError {}
}
