package com.example.exception_handling.criminalrecord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class CriminalRecordConfiguration {

    @Bean
    public CriminalReportFacade criminalReportFacade(CriminalRecordRepository criminalRecordRepository) {
        return new CriminalReportFacade(criminalRecordRepository);
    }

    @Bean
    public CriminalRecordRepository criminalRecordRepository() {
        return new CriminalRecordRepository();
    }
}
