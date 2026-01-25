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
    public Loan findById(int loanId) {
        String sql = "SELECT * FROM loans WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loan(
                        rs.getInt("id"),
                        rs.getInt("member_id"),
                        rs.getInt("book_id"),
                        rs.getDate("loan_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getDate("return_date") == null
                                ? null
                                : rs.getDate("return_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public Loan createLoan(Loan loan) {
        String sql = "INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date)\n" +
                "            VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, loan.getMemberId());
            ps.setInt(2, loan.getBookId());
            ps.setDate(3, Date.valueOf(loan.getLoanDate()));
            ps.setDate(4, Date.valueOf(loan.getDueDate()));
            if (loan.getReturnDate() != null) {
                ps.setDate(5, Date.valueOf(loan.getReturnDate()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                loan.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating loan", e);
        }
        return loan;
    }
    @Override
    public List<Loan> findActiveLoansByMemberId(int memberId) {
        String sql = """
            SELECT * FROM loans
            WHERE member_id = ? AND return_date IS NULL
            """;
        List<Loan> loans = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan(
                            rs.getInt("id"),
                            rs.getInt("member_id"),
                            rs.getInt("book_id"),
                            rs.getDate("loan_date").toLocalDate(),
                            rs.getDate("due_date").toLocalDate(),
                            null
                    );
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding loans", e);
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
