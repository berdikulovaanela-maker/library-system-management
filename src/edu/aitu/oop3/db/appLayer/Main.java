import edu.aitu.oop3.db.LoanManagementComponent.Repository.Interface.LoanRepository;
import edu.aitu.oop3.db.LoanManagementComponent.Repository.LoanRepositoryImpl;
import edu.aitu.oop3.db.LoanManagementComponent.Services.BorrowBookService;
import edu.aitu.oop3.db.LoanManagementComponent.Services.CurrentLoansService;
import edu.aitu.oop3.db.LoanManagementComponent.Services.ReturnBookService;
import edu.aitu.oop3.db.CatalogComponent.Service.AvailableBooksService;
import edu.aitu.oop3.db.CatalogComponent.Service.FindBooksByType;
import edu.aitu.oop3.db.ReportingComponent.LoanReportForMemberService;
import edu.aitu.oop3.db.appLayer.Controller.LibrarySystem;
import edu.aitu.oop3.db.Infrastructure.db.DatabaseConnection;
import edu.aitu.oop3.db.Infrastructure.db.PostgresDB;
import edu.aitu.oop3.db.CatalogComponent.Repository.BookRepositoryImpl;
import edu.aitu.oop3.db.MemberManagementComponent.Repository.MemberRepositoryImpl;
import edu.aitu.oop3.db.CatalogComponent.Repository.Interface.BookRepository;
import edu.aitu.oop3.db.Infrastructure.db.IDB;
import edu.aitu.oop3.db.MemberManagementComponent.Repository.Interface.MemberRepository;
import edu.aitu.oop3.db.LoanManagementComponent.Policy.FinePolicy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

void main() {
    IO.println("Connecting to Supabase...");
    try (Connection connection = DatabaseConnection.getConnection()) {
        IO.println("Connected successfully!");
        String sql = "SELECT CURRENT_TIMESTAMP";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                IO.println("Database time: " + rs.getTimestamp(1));
            }
        }
    } catch (SQLException e) {
        IO.println("Error while connecting to database:");
        e.printStackTrace();
    }
    IDB db = new PostgresDB("jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require", "postgres.uhdkdzvvhltmizldvoix", "library_it-2508");
    BookRepository bookRepo = new BookRepositoryImpl(db);
    MemberRepository memberRepo = new MemberRepositoryImpl(db);
    LoanRepository loanRepo = new LoanRepositoryImpl(db);
    FinePolicy fineCalc =  new FinePolicy();
    AvailableBooksService availableBooksService = new AvailableBooksService(bookRepo);
    CurrentLoansService currentLoansService = new CurrentLoansService(loanRepo);
    ReturnBookService returnBookService = new ReturnBookService(bookRepo,loanRepo,fineCalc);
    BorrowBookService borrowBookService = new BorrowBookService(memberRepo,bookRepo,loanRepo);
    LoanReportForMemberService loanReportForMemberService = new LoanReportForMemberService(loanRepo,memberRepo);
    FindBooksByType findBooksByType = new FindBooksByType(bookRepo);
    LibrarySystem system = new LibrarySystem(availableBooksService,currentLoansService,returnBookService,borrowBookService,loanReportForMemberService,findBooksByType);
    system.run();
}
