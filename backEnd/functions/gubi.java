
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class gubi {
    private Connection connection;

    public gubi(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> getUserById(int userId) {
        Map<String, String> userMap = new HashMap<>();
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userMap.put("username", resultSet.getString("username"));
                        userMap.put("password", resultSet.getString("password"));
                        userMap.put("email", resultSet.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMap;
    }
}
