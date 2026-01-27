package edu.aitu.oop3.db.entities;

public class Ebook extends Book{
    private String format;
    public Ebook(int id, String title, String author, int year, boolean available,Object extra) {
        super(id, title, author, year, available);
        this.format = format;
    }
}
