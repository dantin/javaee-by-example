package ex01;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server started listening 8080 port.
 * <p>
 * The resource dir is 'project/webroot', you can ask for 'http://localhost:8080/index.html'.
 * Also you can request the url that not exist, then the server response error.
 *
 * @version 1.0 2018-07-11
 */
public class HttpServer {
    /**
     * WEB_ROOT is the directory where out HTML and other files reside.
     * For this package, WEB_ROOT is the "webroot" directory under the working directory.
     *
     * The working directory is the location in the file system from where the `java` command was invoked.
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    // shutdown command.
    private static final String SHUTDOWN = "/SHUTDOWN";

    // shutdown command flag.
    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            // backlog is 1, that is the queue of request.
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            try {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // Create & parse Request object.
                Request request = new Request(inputStream);
                request.parse();

                // Create Response object.
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                // Close socket.
                socket.close();

                // Check if the previous URI is a shutdown command.
                shutdown = request.getUri().equals(SHUTDOWN);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }
}
