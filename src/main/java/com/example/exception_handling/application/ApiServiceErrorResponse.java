package com.example.exception_handling.application;

public record ApiServiceErrorResponse(int statusCode, String message) {}
