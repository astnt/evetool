package com.appspot.evetool;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 14, 2010
 * Time: 10:04:19 AM
 */
@Singleton
public class LoginServlet extends HttpServlet {
  public static final String PATH = "/login";
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    if (user != null) {
      response.setContentType("text/plain");
      response.getWriter().println("Hello, " + user.getNickname());
    } else {
      response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
    }
  }
}
