package edu.aitu.oop3.db.entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book() {}
    public Book(int id, String title, String author, int year, boolean available) {
        this.id = id;
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setAvailable(available);
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

    public void setId(int id) {
        this.id = id;
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
    public boolean isAvailable() {
        return available;
    }
    @Override
    public String toString() {
        return " Book:" + id + ", title = " + title + ", author = " + author + ", year = " + year + ", available = " + available + ".";
    }
}
