package edu.aitu.oop3.db.CatalogComponent.Factory;

import edu.aitu.oop3.db.CoreComponent.Entities.Book;
import edu.aitu.oop3.db.CoreComponent.Entities.Ebook;
import edu.aitu.oop3.db.CoreComponent.Entities.PrintedBook;
import edu.aitu.oop3.db.CoreComponent.Entities.ReferenceBook;


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


