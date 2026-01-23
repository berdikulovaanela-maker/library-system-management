package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.Exceptions.BookAlreadyOnLoanException;
import edu.aitu.oop3.db.Exceptions.MemberNotFoundException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;

public class BorrowBookService {
    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    public BorrowBookService(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }
    public void execute(int bookID, int memberId) {
        if (memberRepository.findMemberById(memberId) == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + "is not found");
        }
        Book book = bookRepository.findById(bookID);
        if (book == null || !book.isAvailable()) {
            throw new BookAlreadyOnLoanException("Book with ID " + bookID + " is already borrowed or doesn't exist");
        }
        System.out.println("The book successfully borrowed!");
    }
}
