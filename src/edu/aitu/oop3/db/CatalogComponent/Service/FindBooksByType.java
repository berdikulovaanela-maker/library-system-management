package edu.aitu.oop3.db.CatalogComponent.Service;

import edu.aitu.oop3.db.CatalogComponent.Repository.Interface.BookRepository;
import edu.aitu.oop3.db.CoreComponent.Entities.Book;
import edu.aitu.oop3.db.CoreComponent.Entities.Ebook;
import edu.aitu.oop3.db.CoreComponent.Entities.PrintedBook;
import edu.aitu.oop3.db.CoreComponent.Entities.ReferenceBook;

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
