import java.sql.*;
import java.util.*;


public class guinfo {
    private Connection connection;

    public guinfo(Connection connection) {
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
                        userMap.put("id", String.valueOf(resultSet.getInt("id")));
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

    public Map<String, String> getUserByName(String username) {
        Map<String, String> userMap = new HashMap<>();
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userMap.put("id", String.valueOf(resultSet.getInt("id")));
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