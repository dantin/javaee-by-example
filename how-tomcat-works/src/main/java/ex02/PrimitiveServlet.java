package ex02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");

        PrintWriter out = servletResponse.getWriter();
        out.println("HTTP/1.1 OK");
        out.println("Content-Type:text/html");
        out.println("Content-Length:22");
        out.println();
        out.println("Hello, Roses are red.");
        out.print("Violets are blue.");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
