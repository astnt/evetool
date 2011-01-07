package com.appspot.evetool.server.servlet;

import com.appspot.evetool.server.dao.ShipDao;
import com.appspot.evetool.server.factory.PMF;
import com.appspot.evetool.server.model.Ship;
import com.google.inject.Inject;
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
 * Date: Nov 12, 2010
 * Time: 12:13:10 PM
 */
@Singleton
public class ShipServlet extends HttpServlet {
  private ShipDao shipDao;

  @Inject
  public ShipServlet(ShipDao shipDao) {
    this.shipDao = shipDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if ("delete".equals(req.getParameter("action"))) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
        Ship ship = shipDao.getShipByGameId(pm, req.getParameter("gameId"));
        pm.deletePersistent(ship);
      } finally {
        pm.close();
      }
    }
  }
}
