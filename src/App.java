import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Replace with your actual database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/DBblog";
        String username = "root";
        String password = "morootok";

        // Create a DB object to perform database operations
        DbConnection db = new DbConnection(url, username, password);
        //RegistrationDb registrationDb = new RegistrationDb(db.getConnection());
        gubi gubiInstance = new gubi(db.getConnection());
        gubn gubname = new gubn(db.getConnection());

        // Insert a user
        //registrationDb.insertUser("ibrahim ihssan", "iibr123", "ibrahimihsan@example.com");
        Map<String, String> userMap = gubiInstance.getUserById(14);
        System.out.println("User Info: " + userMap);



         // Get user by name
         Map<String, String> userMap1 = gubname.getUserByName("ibrahim yaseeen");
         System.out.println("User Info: " + userMap1);
        // Close the database connection
        db.closeConnection();
    }
}

