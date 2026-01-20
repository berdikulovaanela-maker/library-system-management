package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.Exceptions.BookAlreadyOnLoanException;
import edu.aitu.oop3.db.Exceptions.MemberNotFoundException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import java.util.Date;


public class BorrowBookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    public BorrowBookService(BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository =  loanRepository;
    }

    public void execute(int bookID, int memberId) {
        if (memberRepository.findMemberById(memberId) == null) {
            throw new MemberNotFoundException("Читатель с ID " + memberId + " не найден.");
        }
        Book book = bookRepository.findById(bookID);
        if (book == null || !book.isAvailable()) {
            throw new BookAlreadyOnLoanException("Книга уже выдана или не существует.");
        }
        System.out.println("Книга успешно выдана через BorrowBookService.");
    }


}
