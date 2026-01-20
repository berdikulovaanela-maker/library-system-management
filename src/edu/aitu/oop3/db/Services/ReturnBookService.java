package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.repositories.BookRepositoryImpl;
import edu.aitu.oop3.db.repositories.LoanRepositoryImpl;
import edu.aitu.oop3.db.repositories.MemberRepositoryImpl;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ReturnBookService {
    private final BookRepositoryImpl bookRepository;
    private final LoanRepositoryImpl loanRepository;
    private final MemberRepositoryImpl memberRepository;
    public ReturnBookService(BookRepositoryImpl bookRepository, LoanRepositoryImpl loanRepository,  MemberRepositoryImpl memberRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
    }
    public void execute(int loanId) {
        Loan loan = loanRepository.findById(loanId);
        if (loan == null || loan.getReturnDate() == null) {
            throw new RuntimeException("Loan not found or already returned.");
        }
        LocalDate today = LocalDate.now();
        if (today.isAfter(loan.getReturnDate())) {
            throw new LoanOverdueException("Book is overdue! Due date was " + loan.getDueDate());
        }
        loanRepository.updateLoanStatus(loan);
        bookRepository.updateBookAvailability(loan.getBookId(),true);
        System.out.println("Book successfully returned!");
    }
}