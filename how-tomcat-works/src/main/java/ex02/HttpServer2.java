package ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version 1.0 2018-07-11
 */
public class HttpServer2 {
    // shutdown command
    public static final String SHUTDOWN = "/shutdown";

    // shutdown flag
    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                // create & parse Request object.
                Request request = new Request(inputStream);
                request.parse();

                // create Response object.
                Response response = new Response(outputStream);
                response.setRequest(request);

                // check if this is a request for a servlet or a static resource
                // a request for servlet begins with "/servlet"
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }

                // check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close socket.
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }
}
