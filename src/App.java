
public class App {
<<<<<<< HEAD
    public static void main(String[] args) {
        // Replace with your actual database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/DBblog";
        String username = "root";
        String password = "morootok";

        // Create a DB object to perform database operations
        db db = new db(url, username, password);

        // Insert a user
        db.insertUser("ibrahim yaseeen", "yaseeniq", "ibrahimyaseen@example.com");

        // Get and print all users
        db.getUsers();
        //db.getUserById(1);
        // Close the database connection
        //db.closeConnection();
=======
    public static void main(String[] args) throws Exception {
        System.out.println("ibrahim, World!");
>>>>>>> master
    }
}
