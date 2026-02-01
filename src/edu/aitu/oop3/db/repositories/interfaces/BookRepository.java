package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    void updateBookAvailability(int bookId, boolean available);


}
