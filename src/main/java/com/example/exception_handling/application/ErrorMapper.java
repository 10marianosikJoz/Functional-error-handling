package com.example.exception_handling.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class ErrorMapper {

    private final ObjectMapper objectMapper;

    ErrorMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    ApiServiceErrorResponse mapToAppropriateErrorType(GeneralError error) {
        return switch (error) {
            case CriminalReportError.ReportForPersonNotFoundError ex -> new ApiServiceErrorResponse(404, "Criminal Report for given person not found.");
            case PersonError.PersonNotFoundError ex -> new ApiServiceErrorResponse(404, "Person with given surname does not exists.");
            case ServiceError.DatabaseError ex -> new ApiServiceErrorResponse(500, "Internal server error.");
            case CriminalReportError.CriminalReportUnexpectedError ex -> new ApiServiceErrorResponse(500, "Internal server error.");
            case ServiceError.CriminalReportServiceError ex -> new ApiServiceErrorResponse(500, "Internal server error");
        };
    }

    ResponseEntity<String> constructSuccessfulResponse(Object responseBody) {
        return ResponseEntity.status(200).body(toJson(responseBody));
    }

    ResponseEntity<String> constructErrorResponse(ApiServiceErrorResponse errorResponse) {
        return ResponseEntity.status(errorResponse.statusCode()).body(toJson(errorResponse));
    }

    private String toJson(Object object) {
        return Try.of(() -> objectMapper.writeValueAsString(object))
                .getOrElseThrow(e -> new RuntimeException("Non serialized response", e));
    }
}
