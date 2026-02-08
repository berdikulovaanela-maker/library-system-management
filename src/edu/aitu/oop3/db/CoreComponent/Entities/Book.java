package edu.aitu.oop3.db.CoreComponent.Entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available;
    private String type;
    public Book(int id, String title, String author, int year, boolean available, String type) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setAvailable(available);
        setType(type);
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
    public void setType(String type) {this.type = type;}
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
    public String getType() {return type;}
    public int getYear() {
        if(year<1500 || year>2026){
            throw new IllegalArgumentException("Impossible to set year");
        }
        return year;
    }
    public boolean isAvailable() {
        return available;
    }
    @Override
    public String toString() {
        return " Book["+ "id:" + id + ", title: " + title + ", author: " + author + ", year: " + year + ", available: " + available + "type: " + type + ']';
    }
}
