package edu.aitu.oop3.db.entities;

public class PrintedBook  Book{
    public PrintedBook(int id, String title, String author, int year, boolean available, String type) {
        super(id, title, author, year, available,type);
    }

    @Override
    public String getType() {
        return "Printed Book";
    }
}
