package com.example.exception_handling.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class PersonConfiguration {

    @Bean
    public PersonFacade personFacade(PersonRepository personRepository) {
        return new PersonFacade(personRepository);
    }

    @Bean
    public PersonRepository personRepository() {
        return new PersonRepository();
    }
}
