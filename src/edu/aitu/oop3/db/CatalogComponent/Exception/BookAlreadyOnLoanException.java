package edu.aitu.oop3.db.CatalogComponent.Exception;

public class BookAlreadyOnLoanException extends RuntimeException {
    public BookAlreadyOnLoanException(String message) {
        super(message);
    }
}