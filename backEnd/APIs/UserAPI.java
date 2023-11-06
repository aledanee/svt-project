import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class UserAPI {
    public static void main(String[] args) throws Exception {
        int port = 8080; // Set your desired port number

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/user", new UserHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port " + port);
    }

    static class UserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            // Create an instance of the DB class
            db db = new db("jdbc:mysql://localhost:3306/DBblog", "root", "morootok");

            if ("GET".equals(exchange.getRequestMethod())) {
                // Get the user ID from the URL
                String path = exchange.getRequestURI().getPath();
                String[] pathSegments = path.split("/");
                if (pathSegments.length >= 3) {
                    try {
                        int userId = Integer.parseInt(pathSegments[2]);

                        // Fetch user information from the database
                        String userInfo = db.getUserById(userId); // Replace with your actual database code

                        // Send the user information as a response
                        exchange.sendResponseHeaders(200, userInfo.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(userInfo.getBytes());
                        os.close();
                    } catch (NumberFormatException e) {
                        // Invalid user ID
                        exchange.sendResponseHeaders(400, 0);
                        exchange.getResponseBody().close();
                    }
                } else {
                    // Invalid URL
                    exchange.sendResponseHeaders(400, 0);
                    exchange.getResponseBody().close();
                }
            } else {
                // Unsupported HTTP method
                exchange.sendResponseHeaders(405, 0);
                exchange.getResponseBody().close();
            }
        }
    }
}
