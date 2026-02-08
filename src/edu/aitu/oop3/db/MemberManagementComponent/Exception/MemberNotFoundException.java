package edu.aitu.oop3.db.MemberManagementComponent.Exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}