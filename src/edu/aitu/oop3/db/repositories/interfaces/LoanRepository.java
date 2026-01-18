package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Loan;

import java.util.List;

public interface LoanRepository {
    Loan createLoan(Loan loan);
    List<Loan> findActiveLoansByMemberId(int memberId);
    void updateLoanStatus(Loan loan);
}
