package edu.aitu.oop3.db.Exceptions;

public class BookAlreadyOnLoanException extends RuntimeException {
    public BookAlreadyOnLoanException(String message) {
        super(message);
    }
}