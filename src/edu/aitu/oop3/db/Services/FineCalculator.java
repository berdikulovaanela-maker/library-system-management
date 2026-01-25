package edu.aitu.oop3.db.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    private final int fine_per_day=50;
    public int execute(LocalDate dueDate, LocalDate returnDate) {
        int fine = 0;
        if (returnDate.isAfter(dueDate)) {
            int overdueDays = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
            fine = overdueDays * fine_per_day;
        }
        return fine;
    }
}