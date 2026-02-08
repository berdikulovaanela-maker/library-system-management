package edu.aitu.oop3.db.LoanManagementComponent.Exception;

public class LoanOverdueException extends RuntimeException {
    public LoanOverdueException(String message) {
        super(message);
    }
}