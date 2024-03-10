package com.example.exception_handling.criminalrecord;

class ReportForPersonNotFound extends RuntimeException {

    ReportForPersonNotFound(String message) {
        super(message);
    }
}
