package ex02;

import java.io.IOException;

/**
 * @version 1.0 2018-07-12
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
