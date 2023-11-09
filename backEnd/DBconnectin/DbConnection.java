
import java.sql.*;

public class DbConnection {
   
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DbConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        try {
            // Initialize the database connection in the constructor
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    

    public void closeConnection() {
        try {
            // Close the database connection
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
};
