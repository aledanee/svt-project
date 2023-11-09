import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;

public class gitui {
    private static guinfo guinfoInstance;

    public static void main(String[] args) throws IOException {
        // Replace with your actual database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/DBblog";
        String username = "root";
        String password = "morootok";

        // Create a DB object to perform database operations
        DbConnection db = new DbConnection(url, username, password);
        guinfoInstance = new guinfo(db.getConnection());

        // Create an HTTP server on port 8080
        int port = 8080; // Set your desired port number
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server is running on port :     " + port);
        // Set up context for handling requests
        String pathid = "/api/users/id";
        String pathname = "/api/users/name";
        server.createContext(pathid, new UserIdHandler());
        server.createContext(pathname, new UserNameHandler());

        System.out.println("path id :      " + pathid + "  put /id");
        System.out.print("path name :      " + pathname + "  put /name");


        // Start the server
        server.setExecutor(null);
        server.start();
    }

    static class UserIdHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String userIdStr = path.substring(path.lastIndexOf('/') + 1);
            
            try {
                int userId = Integer.parseInt(userIdStr);
                Map<String, String> userMap = guinfoInstance.getUserById(userId);
                sendResponse(exchange, userMap.toString());
            } catch (NumberFormatException e) {
                sendResponse(exchange, "Invalid user ID");
            }
        }
    }

    static class UserNameHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String username = path.substring(path.lastIndexOf('/') + 1);
            
            Map<String, String> userMap = guinfoInstance.getUserByName(username);
            sendResponse(exchange, userMap.toString());
        }
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
