package edu.aitu.oop3.db.entities;

public class ReferenceBook extends Book {
    private boolean inLibraryOnly;
    public ReferenceBook(int id, String title, String author, int year, boolean available,Object extra) {
        super(id, title, author, year, available);
        this.inLibraryOnly = inLibraryOnly;
    }
}
