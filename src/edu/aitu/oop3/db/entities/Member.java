package edu.aitu.oop3.db.entities;

public class Member {
    private static int id;
    private static String firstName;
    private static String lastName;
    private String email;
    public Member(int id, String firstName, String lastName, String email) {
        setId(id);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setId(int id) {
        this.id = id;
    }
    public static int getId() {
        return id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public static String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public static String getLastName() {
        return lastName;
    }
    public void setEmail(String email) {
        if(email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "Member name: " + firstName + "Last name: " + lastName + " email: " + email;
    }
}
