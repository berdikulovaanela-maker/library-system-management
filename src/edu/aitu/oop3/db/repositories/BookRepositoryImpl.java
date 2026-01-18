package edu.aitu.oop3.db.repositories;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.IDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private final IDB db;
    public BookRepositoryImpl (IDB db) {
        this.db = db;
    }
    @Override
    public Book findById(int id) {
        String sql="SELECT id, title, author, year, available FROM books WHERE id = ?";
        try {
            Connection conn= db.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year"), rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        String sql="SELECT * FROM books WHERE title = ?";
        try{
            Connection conn = db.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,title);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year"), rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findByAuthor(String author) {
        String sql="SELECT * FROM books WHERE author = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,author);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year"), rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAvailableBooks() {
        List<Book> books=new ArrayList<>();
        String sql="SELECT * FROM books WHERE available = true";
        try (Connection conn = db.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Book book = new Book( rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBookAvailability(int bookId, boolean available) {
        String sql="UPDATE books SET available = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setBoolean(1,available);
            ps.setInt(2,bookId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
