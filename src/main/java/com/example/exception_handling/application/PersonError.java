package com.example.exception_handling.application;

sealed interface PersonError extends GeneralError {

    record PersonNotFoundError(String name, String surname) implements PersonError {}
}
