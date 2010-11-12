package com.appspot.evetool.servlet;

import com.appspot.evetool.factory.PMF;
import com.appspot.evetool.model.Ship;
import com.google.inject.Singleton;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 8, 2010
 * Time: 5:21:04 PM
 */
@Singleton
public class TestActionServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Ship ship = new Ship("00", "Test", "Cruiser", "Caldari", null);
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      pm.makePersistent(ship);
    } finally {
      pm.close();
    }
    
  }
}
