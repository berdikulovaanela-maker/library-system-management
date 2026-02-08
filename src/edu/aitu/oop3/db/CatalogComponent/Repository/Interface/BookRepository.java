package edu.aitu.oop3.db.CatalogComponent.Repository.Interface;

import edu.aitu.oop3.db.CoreComponent.Entities.Book;
import edu.aitu.oop3.db.CoreComponent.Repository.Interface.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    void updateBookAvailability(int bookId, boolean available);
}
