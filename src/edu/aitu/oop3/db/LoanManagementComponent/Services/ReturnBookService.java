package edu.aitu.oop3.db.LoanManagementComponent.Services;

import edu.aitu.oop3.db.LoanManagementComponent.Policy.FinePolicy;
import edu.aitu.oop3.db.LoanManagementComponent.Exception.LoanOverdueException;
import edu.aitu.oop3.db.LoanManagementComponent.Repository.Interface.LoanRepository;
import edu.aitu.oop3.db.CoreComponent.Entities.Loan;
import edu.aitu.oop3.db.CatalogComponent.Repository.Interface.BookRepository;

import java.time.LocalDate;
public class ReturnBookService {
    public final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final FinePolicy finePolicy;
    public ReturnBookService(BookRepository bookRepository, LoanRepository loanRepository, FinePolicy finePolicy) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.finePolicy = finePolicy;
    }
    public void execute(int loanId) {
        Loan loan = loanRepository.findById(loanId);
        if (loan == null) {
            throw new RuntimeException("Loan not found");
        }
        if(loan.getReturnDate() != null) {
            throw new RuntimeException("Loan already returned");
        }
        LocalDate today = LocalDate.now();
        if (today.isAfter(loan.getDueDate())) {
            int fine = finePolicy.calculateFine(loan.getDueDate(), today);
            loan.setReturnDate(today);
            loanRepository.updateLoanStatus(loan);
            bookRepository.updateBookAvailability(loan.getBookId(),true);
            throw new LoanOverdueException("Book is overdue! Due date was " + loan.getDueDate() + ".Your fine is " + fine);
        }
        loan.setReturnDate(today);
        loanRepository.updateLoanStatus(loan);
        bookRepository.updateBookAvailability(loan.getBookId(),true);
    }
}