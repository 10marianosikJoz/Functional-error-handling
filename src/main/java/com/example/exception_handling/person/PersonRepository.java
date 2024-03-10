package com.example.exception_handling.person;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class PersonRepository {

    Optional<Person> findPerson(String name, String surname) {
        return switch (ThreadLocalRandom.current().nextInt(0, 15)) {
            case 0 -> throw new RuntimeException("Error");
            case 1 -> Optional.empty();
            default -> Optional.of(new Person(name, surname));
        };
    }
}
