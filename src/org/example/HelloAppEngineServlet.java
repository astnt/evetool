package org.example;

import com.google.inject.Singleton;

import java.io.IOException;
import javax.servlet.http.*;

@Singleton
public class HelloAppEngineServlet extends HttpServlet {
  public static final String PATH = "/helloappengine";
  
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println("Hello, world");
  }
}