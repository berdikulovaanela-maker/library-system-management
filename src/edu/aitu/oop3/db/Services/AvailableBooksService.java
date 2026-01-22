package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.repositories.BookRepositoryImpl;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;

import java.util.List;

public class AvailableBooksService {
    private final BookRepository bookRepository;
    public AvailableBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> execute() {
        List<Book> books = bookRepository.findAvailableBooks();
        System.out.println("List of available Books:");
        if(books.isEmpty()) {
            throw new RuntimeException("Books not available");
        }
        return books;
    }
}

