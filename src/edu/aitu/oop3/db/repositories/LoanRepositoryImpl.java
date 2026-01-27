package edu.aitu.oop3.db.repositories;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.db.IDB;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepositoryImpl implements LoanRepository {
    private final IDB db;

    public LoanRepositoryImpl(IDB db) {
        this.db = db;
    }
    @Override
    public Loan findById(Integer integer) {
        String sql = "SELECT * FROM loans WHERE member_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loan.Builder().id(rs.getInt("id")).memberId(rs.getInt("member_id")).bookId(rs.getInt("book_id")).loanDate(rs.getDate("loan_date").toLocalDate()).dueDate(rs.getDate("due_date").toLocalDate()).returnDate(rs.getDate("return_date") == null
                        ? null
                        : rs.getDate("return_date").toLocalDate()).build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Loan> findAll() {
        String sql = "SELECT * FROM loans where id = ?";
        List<Loan> loans = new ArrayList<>();
        try (Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan.Builder().id(rs.getInt("id")).memberId(rs.getInt("member_id")).bookId(rs.getInt("book_id")).loanDate(rs.getDate("loan_date").toLocalDate()).dueDate(rs.getDate("due_date").toLocalDate()).returnDate(null).build();
                loans.add(loan);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loans;
    }

    @Override
    public void save(Loan entity) {
        String sql = "INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date)\n" +
                "            VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getMemberId());
            ps.setInt(2, entity.getBookId());
            ps.setDate(3, Date.valueOf(entity.getLoanDate()));
            ps.setDate(4, Date.valueOf(entity.getDueDate()));
            if (entity.getReturnDate() != null) {
                ps.setDate(5, Date.valueOf(entity.getReturnDate()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating loan", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM loans WHERE id = ?";
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error deleting loan", e);
        }
    }

    @Override
    public List<Loan> findByMember_id(int member_id) {
        String sql = "SELECT * FROM loans WHERE member_id = ?";
        List<Loan> loans = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, member_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan.Builder().id(rs.getInt("id")).memberId(rs.getInt("member_id")).bookId(rs.getInt("book_id")).loanDate(rs.getDate("loan_date").toLocalDate()).dueDate(rs.getDate("due_date").toLocalDate()).returnDate(rs.getDate("return_date") == null
                        ? null
                        : rs.getDate("return_date").toLocalDate()).build();
                loans.add(loan);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loans;
    }

    @Override
    public void updateLoanStatus(Loan loan) {
        String sql = """
        UPDATE loans
        SET return_date = ?
        WHERE id = ?
        """;
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(loan.getReturnDate())); // устанавливаем текущую дату
            ps.setInt(2, loan.getId()); // используем id из объекта Loan
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error update loan status", e);
        }
    }
    }
