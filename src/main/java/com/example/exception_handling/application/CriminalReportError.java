package com.example.exception_handling.application;

public sealed interface CriminalReportError extends GeneralError {

    record ReportForPersonNotFoundError(String surname) implements CriminalReportError {}
    record CriminalReportUnexpectedError(Throwable message) implements CriminalReportError {}
}
