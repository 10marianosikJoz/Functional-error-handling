package com.example.exception_handling.application;

public sealed interface GeneralError permits PersonError,
                                             CriminalReportError,
                                             ServiceError{}
