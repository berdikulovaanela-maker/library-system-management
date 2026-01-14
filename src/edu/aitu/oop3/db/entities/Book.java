package edu.aitu.oop3.db.entities;

public class Book {
    private int id;
    private static int idGen=1;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book() {
        id = idGen++;
        available = true;
    }
    public Book(int id, String title, String author, int year, boolean available) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }


}
