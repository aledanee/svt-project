import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class LikeApi {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/like", new LikeHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class LikeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Parse request parameters to get blogId and userId
                // For simplicity, assuming the parameters are passed in URL like: /like?blogId=123&userId=456
                String query = exchange.getRequestURI().getQuery();
                int blogId = 0, userId = 0;

                String[] queryParams = query.split("&");
                for (String param : queryParams) {
                    String[] pair = param.split("=");
                    if (pair.length == 2) {
                        if (pair[0].equals("blogId")) {
                            blogId = Integer.parseInt(pair[1]);
                        } else if (pair[0].equals("userId")) {
                            userId = Integer.parseInt(pair[1]);
                        }
                    }
                }

                // Perform like operation using the provided blogId and userId
                likeBlog(blogId, userId);

                String response = "Liked blog " + blogId + " by user " + userId;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Invalid request method";
                exchange.sendResponseHeaders(405, response.length()); // Method not allowed
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    // Method to like a blog
    public static void likeBlog(int blogId, int userId) {
        // Perform like operation here using the blogId and userId
        // For example:
        // Like like = new LikeFun().new Like(blogId, userId);
        // like.like();
        System.out.println("User " + userId + " liked blog " + blogId);
        // Implement your logic to interact with the database or perform necessary operations.
    }
}


//http://localhost:8000/like?blogId=<blogId>&userId=<userId>
