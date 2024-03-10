package com.example.exception_handling.criminalrecord;

import java.util.concurrent.ThreadLocalRandom;

class CriminalRecordRepository {

     CriminalReport findCriminalReport(String surname) {
        return switch (ThreadLocalRandom.current().nextInt(0, 15)) {
            case 0 -> throw new ReportDatabaseException("Database exception");
            case 1 -> throw new ReportForPersonNotFound("Report for person: " + surname + " not found");
            default -> new CriminalReport(surname + " is wanted" + surname, true);
        };
    }
}
