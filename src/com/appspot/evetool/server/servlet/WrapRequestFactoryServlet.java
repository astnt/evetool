package com.appspot.evetool.server.servlet;

import com.google.gwt.requestfactory.server.RequestFactoryServlet;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 8:56 PM
 */
@Singleton
public class WrapRequestFactoryServlet extends RequestFactoryServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setCharacterEncoding("utf8");
    super.doPost(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("utf8");
    super.doGet(request, response);
  }
}
