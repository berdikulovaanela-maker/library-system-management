import edu.aitu.oop3.db.db.DatabaseConnection;
import edu.aitu.oop3.db.db.PostgresDB;
import edu.aitu.oop3.db.repositories.BookRepositoryImpl;
import edu.aitu.oop3.db.repositories.interfaces.BookRepository;
import edu.aitu.oop3.db.db.IDB;

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

}
