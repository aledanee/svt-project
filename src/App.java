import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Replace with your actual database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/DBblog";
        String username = "root";
        String password = "morootok";

        // Create a DB object to perform database operations
        DbConnection db = new DbConnection(url, username, password);
        RegistrationDb registrationDb = new RegistrationDb(db.getConnection());
        gubi gubiInstance = new gubi(db.getConnection());
        // Insert a user
        //registrationDb.insertUser("ibrahim ihssan", "iibr123", "ibrahimihsan@example.com");
        Map<String, String> userMap = gubiInstance.getUserById(14);
        System.out.println("User Info: " + userMap);

        // Close the database connection
        db.closeConnection();
    }
}

