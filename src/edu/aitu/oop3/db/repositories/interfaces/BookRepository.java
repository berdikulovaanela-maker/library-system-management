package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Book;

import java.util.List;

public interface BookRepository {
    Book findById(int id);
    Book findByTitle(String title);
    Book findByAuthor(String author);
    List<Book> findAvailableBooks();
    void updateBookAvailability(int bookId, boolean available);


}
