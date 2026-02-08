package edu.aitu.oop3.db.CatalogComponent.Service;

import edu.aitu.oop3.db.CatalogComponent.Repository.Interface.BookRepository;
import edu.aitu.oop3.db.CoreComponent.Entities.Book;

import java.util.List;

public class AvailableBooksService{
    private final BookRepository bookRepository;
    public AvailableBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> execute() {
        return bookRepository.findAll().stream().filter(Book::isAvailable).toList();
    }
}

