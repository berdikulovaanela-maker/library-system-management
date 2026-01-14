package edu.aitu.oop3.db.entities;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    public Member(){}
    public Member(int id, String firstName, String lastName, String email) {
        this.id = id;
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "Member name: " + firstName + " " + lastName + " email: " + email;
    }
}
