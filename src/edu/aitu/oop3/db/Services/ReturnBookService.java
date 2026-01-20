package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;

import java.time.ZoneId;
import java.util.Date;

public class ReturnBookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public ReturnBookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public void execute(int loanId) {
        Loan loan = null;

        if (loan == null || loan.getReturnDate() != null) {
            throw new RuntimeException("Заем не найден или книга уже возвращена.");
        }
        Date actualReturnDate = new Date();
        Date duedate = Date.from(loan.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (actualReturnDate.after(duedate)) {
            throw new LoanOverdueException("Книга просрочена! Срок был до " + loan.getDueDate());
        }
        System.out.println("Книга успешно возвращена через ReturnBookService.");
    }
}