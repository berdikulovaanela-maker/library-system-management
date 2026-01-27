package edu.aitu.oop3.db.entities;

public class PrintedBook extends Book{
    private int pages;
    public PrintedBook(int id, String title, String author, int year, boolean available,Object extra) {
        super(id, title, author, year, available);
        this.pages = pages;
    }
}
