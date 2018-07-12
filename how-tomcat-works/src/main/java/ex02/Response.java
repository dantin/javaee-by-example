package ex02;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Response is an implementation of {@link ServletResponse}.
 *
 * @version 1.0 2018-07-11
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE = 1024;

    private static final String OK = "HTTP/1.1 OK\r\n";
    private static final String NOT_FOUND = "HTTP/1.1 404 File Not Found\r\n";
    private static final String CONTENT_TYPE = "Content-Type:text/html\r\n";
    private static final String CONTENT_LENGTH = "Content-Length:%d\r\n";
    private static final String EMPTY_LINE = "\r\n";

    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream outputStream) {
        this.output = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Serve static pages.
     *
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];

        File file = new File(Constants.WEB_ROOT, request.getUri());
        output.write(OK.getBytes());
        output.write(CONTENT_TYPE.getBytes());
        output.write(CONTENT_TYPE.getBytes());
        output.write(EMPTY_LINE.getBytes());
        try (FileInputStream fis = new FileInputStream(file)) {

            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while (ch != -1) {
                output.write(bytes, 0, ch);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }
        } catch (FileNotFoundException e) {
            String errorMessage = NOT_FOUND
                    + CONTENT_TYPE
                    + String.format(CONTENT_LENGTH, 23)
                    + EMPTY_LINE
                    + "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }
    }

    /* implementation of ServletResponse */
    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // autoFlush is true, println() will flush.
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
