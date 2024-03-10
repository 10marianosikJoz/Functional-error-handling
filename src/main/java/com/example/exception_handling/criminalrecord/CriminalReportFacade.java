package com.example.exception_handling.criminalrecord;

import com.example.exception_handling.application.CriminalReportError;
import com.example.exception_handling.application.GeneralError;
import com.example.exception_handling.application.ServiceError;
import io.vavr.control.Either;
import io.vavr.control.Try;

public class CriminalReportFacade {

    private final CriminalRecordRepository criminalRecordRepository;

    public CriminalReportFacade(final CriminalRecordRepository criminalRecordRepository) {
        this.criminalRecordRepository = criminalRecordRepository;
    }

    public Either<GeneralError, CriminalReport> findCriminalReport(String surname) {
        return Try.of(() -> criminalRecordRepository.findCriminalReport(surname)).toEither()
                .mapLeft(criminalReportException ->
                        switch (criminalReportException) {
                            case ReportDatabaseException ex -> new ServiceError.CriminalReportServiceError("Criminal service error");
                            case ReportForPersonNotFound ex -> new CriminalReportError.ReportForPersonNotFoundError("Report for person: " + surname + " not found");
                            default -> new CriminalReportError.CriminalReportUnexpectedError(criminalReportException);
                        });
    }
}
