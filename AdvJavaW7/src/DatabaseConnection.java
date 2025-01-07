import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {
        // Database URL, username, and password (replace these with your actual database credentials)
        String url = "jdbc:mysql://localhost:3306/UserManagementContentApplication"; // Replace with your database URL
        String username = "Zhami"; //your database username
        String password = "123"; //your database password

        // Initialize connection object
        Connection connection = null;

        try {
            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connection successful!");
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        } finally {
            // Close the connection if it was opened
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection.");
                e.printStackTrace();
            }
        }
    }
}
