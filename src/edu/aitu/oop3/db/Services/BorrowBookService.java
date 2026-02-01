package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.Exceptions.BookAlreadyOnLoanException;
import edu.aitu.oop3.db.Exceptions.MemberNotFoundException;
import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;

import java.time.LocalDate;

public class BorrowBookService {
    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    private LoanRepository loanRepository;
    public BorrowBookService(MemberRepository memberRepository, BookRepository bookRepository, LoanRepository loanRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }
    public void execute(int bookID, int memberId) {
        if (memberRepository.findById(memberId) == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " is not found");
        }
        Book book = bookRepository.findById(bookID);
        if (book == null || !book.isAvailable()) {
            throw new BookAlreadyOnLoanException("Book with ID " + bookID + " is already borrowed or doesn't exist");
        }
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);
        Loan loan = new Loan.Builder().id(0).memberId(memberId).bookId(bookID).loanDate(borrowDate).dueDate(dueDate).returnDate(null).build();
        loanRepository.save(loan);
        bookRepository.updateBookAvailability(bookID, false);
    }
}
