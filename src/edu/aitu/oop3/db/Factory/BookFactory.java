package edu.aitu.oop3.db.Factory;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.entities.Ebook;
import edu.aitu.oop3.db.entities.PrintedBook;
import edu.aitu.oop3.db.entities.ReferenceBook;


public class BookFactory {

    public static Book createBook(int id, String title, String author, int year, boolean available, String type) {
        return switch (type.toLowerCase()) {
            case "printed" -> new PrintedBook(id, title, author, year, available, type);
            case "ebook" -> new Ebook(id, title, author, year, available,type);
            case "reference" -> new ReferenceBook(id, title, author, year, available,type);
            default -> throw new IllegalArgumentException("Unknown book type: " + type);
        };
    }
}


