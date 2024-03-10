package com.example.exception_handling.application;

import com.example.exception_handling.criminalrecord.CriminalReportFacade;
import com.example.exception_handling.person.PersonFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "v1/report", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
class CriminalReportEndpoint {

    private final CriminalReportFacade criminalReportFacade;
    private final PersonFacade personFacade;
    private final ErrorMapper errorMapper;

    CriminalReportEndpoint(final CriminalReportFacade criminalReportFacade,
                           final PersonFacade personFacade,
                           final ErrorMapper errorMapper) {

        this.criminalReportFacade = criminalReportFacade;
        this.personFacade = personFacade;
        this.errorMapper = errorMapper;
    }

    @GetMapping
    ResponseEntity<String> findCriminalReport(@RequestParam(defaultValue = "John") String name, @RequestParam(defaultValue = "Rock") String surname) {
        return personFacade.findPerson(name, surname)
                .flatMap(person -> criminalReportFacade.findCriminalReport(person.surname()))
                .mapLeft(errorMapper::mapToAppropriateErrorType)
                .fold(errorMapper::constructErrorResponse, errorMapper::constructSuccessfulResponse);
    }
}
