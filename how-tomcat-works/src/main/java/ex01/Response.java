package ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Response object.
 *
 * @version 1.0 2018-07-11
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;

    private static final String OK = "HTTP/1.1 OK\r\n";
    private static final String NOT_FOUND = "HTTP/1.1 404 File Not Found\r\n";
    private static final String CONTENT_TYPE = "Content-Type:text/html\r\n";
    private static final String CONTENT_LENGTH = "Content-Length:%d\r\n";
    private static final String EMPTY_LINE = "\r\n";

    Request request;
    OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                outputStream.write(OK.getBytes());
                outputStream.write(CONTENT_TYPE.getBytes());
                outputStream.write(String.format(CONTENT_LENGTH, file.length()).getBytes());
                outputStream.write(EMPTY_LINE.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = NOT_FOUND
                        + CONTENT_TYPE
                        + String.format(CONTENT_LENGTH, 23)
                        + EMPTY_LINE
                        + "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
