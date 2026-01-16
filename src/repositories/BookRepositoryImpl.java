package repositories;

import edu.aitu.oop3.db.entities.Book;
import interfaces.BookRepository;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public Book findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findAvailableBooks() {
        return List.of();
    }

    @Override
    public void updateBookAvailability(int bookId, boolean available) {

    }
}
