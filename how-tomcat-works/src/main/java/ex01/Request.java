package ex01;

import java.io.IOException;
import java.io.InputStream;

/**
 * Request object.
 *
 * @version 1.0 2018-07-11
 */
public class Request {
    private static final int BUFFER_SIZE = 2048;

    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuilder request = new StringBuilder(BUFFER_SIZE);
        int i;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        System.out.print(request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * Get resource string.
     *
     * @param requestString standard http format string, e.g. 'GET /index.html'
     * @return resource location, e.g. '/index.html'
     */
    public String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return this.uri;
    }
}
