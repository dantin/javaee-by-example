package ex02;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Process http request for servlet.
 *
 * @version 1.0 2018-07-11
 */
public class ServletProcessor1 {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            // create an URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;

            final File classPath = new File(getClasspath());

            // the forming of repository is taken from the createClassLoader method in org.apache.catalina.startup.ClassLoaderFactory
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class<?> clazz = null;
        try {
            final String fullClassName = this.getClass().getPackage().getName() + "." + servletName;
            clazz = loader.loadClass(fullClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) clazz.newInstance();
            servlet.service(request, response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getClasspath() {
        return this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}
