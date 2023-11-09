import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDb {
    private Connection connection;

    public RegistrationDb(Connection connection) {
        this.connection = connection;
    }

    public void insertUser(String username, String password, String email) {
        try {
            String query = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
