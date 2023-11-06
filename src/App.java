
public class App {
    public static void main(String[] args) {
        // Replace with your actual database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/DBblog";
        String username = "root";
        String password = "morootok";

        // Create a DB object to perform database operations
        db db = new db(url, username, password);

        // Insert a user
        db.insertUser("exampleUser", "password123", "user@example.com");

        // Get and print all users
        db.getUsers();

        // Close the database connection
        db.closeConnection();
    }
}
