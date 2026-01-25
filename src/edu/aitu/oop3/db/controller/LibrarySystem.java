package edu.aitu.oop3.db.controller;

import edu.aitu.oop3.db.Exceptions.BookAlreadyOnLoanException;
import edu.aitu.oop3.db.Exceptions.LoanOverdueException;
import edu.aitu.oop3.db.Exceptions.MemberNotFoundException;
import edu.aitu.oop3.db.Services.AvailableBooksService;
import edu.aitu.oop3.db.Services.BorrowBookService;
import edu.aitu.oop3.db.Services.CurrentLoansService;
import edu.aitu.oop3.db.Services.ReturnBookService;
import edu.aitu.oop3.db.entities.Book;
import edu.aitu.oop3.db.entities.Loan;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private final Scanner scanner = new Scanner(System.in);
    private final AvailableBooksService availableBooksService;
    private final CurrentLoansService currentLoansService;
    private final ReturnBookService returnBookService;
    private final BorrowBookService borrowBookService;
    public LibrarySystem(AvailableBooksService availableBooksService, CurrentLoansService currentLoansService, ReturnBookService returnBookService, BorrowBookService borrowBookService){
        this.availableBooksService = availableBooksService;
        this.currentLoansService = currentLoansService;
        this.returnBookService = returnBookService;
        this.borrowBookService = borrowBookService;
    }
    public void run(){
        while(true){
                System.out.println("Welcome to the Library Management System!");
                menu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1:
                        List<Book> books =availableBooksService.execute();
                        if(books.isEmpty()){
                            System.out.println("No books available");
                        }
                        else {
                            books.forEach(System.out::println);
                        }
                        break;
                    case 2:
                        System.out.println("Enter member ID:");
                        int memberID = scanner.nextInt();
                        scanner.nextLine();
                        List<Loan> loans = currentLoansService.execute(memberID);
                        if(loans.isEmpty()){
                            System.out.println("There is no loans for this member");
                        }
                        else {
                            loans.forEach(System.out ::println);
                        }
                        break;
                    case 3:
                        System.out.println("Enter loan ID:");
                        int loanID = scanner.nextInt();
                        scanner.nextLine();
                        try{returnBookService.execute(loanID);
                            System.out.println("Book has been successfully returned!");}
                        catch (LoanOverdueException e){
                            System.out.println(e.getMessage());}
                        break;
                    case 4:
                        System.out.println("Enter book ID:");
                        int book_ID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter member ID:");
                        int member_ID = scanner.nextInt();
                        scanner.nextLine();
                        try{ borrowBookService.execute(book_ID, member_ID);
                            System.out.println("Book has been borrowed successfully!");}
                        catch(MemberNotFoundException | BookAlreadyOnLoanException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Good Bye!");
                        return;
                    default:
                        System.out.println("Wrong choice!!!");
                }
            }
        }
    public void menu(){
        System.out.println("1. Show Available Books");
        System.out.println("2. Show Active Loans");
        System.out.println("3. Return Book");
        System.out.println("4. Borrow Book");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:");
    }}
