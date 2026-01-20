package edu.aitu.oop3.db.Exceptions;

public class LoanOverdueException extends RuntimeException {
    public LoanOverdueException(String message) {
        super(message);
    }
}