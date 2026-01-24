package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import java.time.LocalDate;
public class ReturnBookService {
    public final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final FineCalculator fineCalculator;
    public ReturnBookService(BookRepository bookRepository, LoanRepository loanRepository, FineCalculator fineCalculator) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.fineCalculator = fineCalculator;
    }
    public void execute(int loanId) {
        Loan loan = loanRepository.findById(loanId);
        if (loan == null || loan.getReturnDate() != null) {
            throw new RuntimeException("Loan not found or already returned.");
        }
        LocalDate today = LocalDate.now();
        if (today.isAfter(loan.getDueDate())) {
            int fine = fineCalculator.execute(loan.getDueDate(), today);
            throw new LoanOverdueException("Book is overdue! Due date was " + loan.getDueDate() + ".Your fine is " + fine);
        }
        loan.setReturnDate(today);
        loanRepository.updateLoanStatus(loan);
        bookRepository.updateBookAvailability(loan.getBookId(),true);
    }
}