package edu.aitu.oop3.db.appLayer.Controller;

import edu.aitu.oop3.db.LoanManagementComponent.Services.BorrowBookService;
import edu.aitu.oop3.db.LoanManagementComponent.Services.CurrentLoansService;
import edu.aitu.oop3.db.LoanManagementComponent.Services.ReturnBookService;
import edu.aitu.oop3.db.CatalogComponent.Service.AvailableBooksService;
import edu.aitu.oop3.db.CatalogComponent.Service.FindBooksByType;
import edu.aitu.oop3.db.CatalogComponent.Exception.BookAlreadyOnLoanException;
import edu.aitu.oop3.db.LoanManagementComponent.Exception.LoanOverdueException;
import edu.aitu.oop3.db.MemberManagementComponent.Exception.MemberNotFoundException;
import edu.aitu.oop3.db.ReportingComponent.LoanReportForMemberService;
import edu.aitu.oop3.db.CoreComponent.Entities.Book;
import edu.aitu.oop3.db.CoreComponent.Entities.Loan;
import edu.aitu.oop3.db.CoreComponent.Entities.LoanReport;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private final Scanner scanner = new Scanner(System.in);
    private final AvailableBooksService availableBooksService;
    private final CurrentLoansService currentLoansService;
    private final ReturnBookService returnBookService;
    private final BorrowBookService borrowBookService;
    private final LoanReportForMemberService loanReportService;
    private final FindBooksByType findBooksByType;
    public LibrarySystem(AvailableBooksService availableBooksService, CurrentLoansService currentLoansService, ReturnBookService returnBookService, BorrowBookService borrowBookService, LoanReportForMemberService loanReportService, FindBooksByType findBooksByType) {
        this.availableBooksService = availableBooksService;
        this.currentLoansService = currentLoansService;
        this.returnBookService = returnBookService;
        this.borrowBookService = borrowBookService;
        this.loanReportService = loanReportService;
        this.findBooksByType = findBooksByType;
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
                        System.out.println("Enter member ID:");
                        int memberId = scanner.nextInt();
                        scanner.nextLine();
                        try{
                            LoanReport report = loanReportService.generateReport(memberId);
                            System.out.println(report);
                        }
                        catch(MemberNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Enter book type:");
                        String book_type = scanner.nextLine();
                        List<Book> booksByType = findBooksByType.findBooksByType(book_type);
                        if(booksByType.isEmpty()){
                            System.out.println("There is no books for this type");
                        }
                        else {
                            booksByType.forEach(System.out::println);
                        }
                        break;
                    case 7:
                        System.out.println("Exiting Library Management System!");
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
        System.out.println("5. Get Loan Report for Member");
        System.out.println("6. Show list of specific type books ");
        System.out.println("7. Exit");
        System.out.println("Enter your choice:");
    }}
