package com.appspot.warandmilitary;

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
 * Date: Feb 16, 2010
 * Time: 9:02:59 PM
 */
@Singleton
public class StatusServlet extends HttpServlet {
  public static final String PATH = "/status";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
      response.sendRedirect(LoginServlet.PATH);
    }
    response.getWriter().append(this.getClass().getCanonicalName());
  }
}
