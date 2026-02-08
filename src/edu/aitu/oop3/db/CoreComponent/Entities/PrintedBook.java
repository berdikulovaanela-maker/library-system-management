package edu.aitu.oop3.db.CoreComponent.Entities;

public class PrintedBook extends Book {
    public PrintedBook(int id, String title, String author, int year, boolean available, String type) {
        super(id, title, author, year, available,type);
    }

    @Override
    public String getType() {
        return "Printed Book";
    }
}
