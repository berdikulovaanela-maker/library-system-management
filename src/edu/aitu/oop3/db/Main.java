import edu.aitu.oop3.db.DatabaseConnection;

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
}