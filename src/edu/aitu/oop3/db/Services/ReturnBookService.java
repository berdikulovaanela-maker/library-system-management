package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import edu.aitu.oop3.db.policies.FinePolicy;
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