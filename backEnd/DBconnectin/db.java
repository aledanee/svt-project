import java.sql.*;

public class db {
   
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public db(String url, String username, String password) {
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

    public void insertUser(String username, String password, String email) {
        try {
            // Prepare the insert statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user (username, password, email) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            // Execute the insert statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUsers() {
        try {
            // Prepare a select statement to get all users
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = statement.executeQuery();

            // Process and print the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String created_at = resultSet.getString("created_at");

                System.out.println("User ID: " + id);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Email: " + email);
                System.out.println("Created At: " + created_at);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserById(int userId) {
        try {
            // Prepare a select statement to get a user by ID
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String created_at = resultSet.getString("created_at");

                // Build a user information string (you can format it as needed)
                return "User ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nCreated At: " + created_at;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Return an empty string or an appropriate error message
        return "User not found";
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
