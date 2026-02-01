package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.entities.Ebook;
import edu.aitu.oop3.db.entities.PrintedBook;
import edu.aitu.oop3.db.entities.ReferenceBook;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;

import java.util.List;

public class FindBooksByType {
    BookRepository bookRepository;
    public FindBooksByType(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findBooksByType(String type){
        return bookRepository.findAll().stream().filter(Book::isAvailable).filter(book -> switch (type.toLowerCase()){
                    case "printed" -> book instanceof PrintedBook;
                    case "ebook" -> book instanceof Ebook;
                    case "reference" -> book instanceof ReferenceBook;
                    default -> false;
                })
                .toList();
        }
    }
