package com.example.exception_handling.person;

import com.example.exception_handling.application.CriminalReportError;
import com.example.exception_handling.application.GeneralError;
import com.example.exception_handling.application.ServiceError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

public class PersonFacade {

    private final PersonRepository personRepository;

    public PersonFacade(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Either<GeneralError, Person> findPerson(String name, String surname) {
        return Try.of(() -> personRepository.findPerson(name, surname))
                .toEither()
                .map(Option::ofOptional)
                .<GeneralError>mapLeft(ServiceError.DatabaseError::new)
                .flatMap(person -> person.toEither(new CriminalReportError.ReportForPersonNotFoundError(surname)));
    }
}
