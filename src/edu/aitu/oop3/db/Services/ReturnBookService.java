package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;
import java.time.LocalDate;
public class ReturnBookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    public ReturnBookService(BookRepository bookRepository, LoanRepository loanRepository,  MemberRepository memberRepository) {
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