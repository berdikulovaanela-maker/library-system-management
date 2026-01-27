package edu.aitu.oop3.db.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FinePolicy {
    private static FinePolicy  instance;
    private final int finePerDay = 500;
    public FinePolicy() {}
    public static FinePolicy getInstance() {
        if(instance  == null) {
            instance = new FinePolicy();
        }
        return instance;
    }
    public int calculateFine(LocalDate dueDate, LocalDate returnDate) {
        int overdueDate = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
        return finePerDay*overdueDate;
    }
}
