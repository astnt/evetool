package com.appspot.evetool.servlet.images;

import com.appspot.evetool.dao.ShipDao;
import com.appspot.evetool.factory.PMF;
import com.appspot.evetool.model.Ship;
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
 * Date: Nov 11, 2010
 * Time: 3:15:56 PM
 */
@Singleton
public class ShipImagesServlet extends HttpServlet {

  private ShipDao shipDao;

  @Inject
  public ShipImagesServlet(ShipDao shipDao) {
    this.shipDao = shipDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    Ship ship = shipDao.getShipByGameId(pm, req.getParameter("gameId"));

    // serve the first image
    resp.setContentType("image/png");
    if (ship != null) {
      resp.getOutputStream().write(ship.getIcon().getBytes());
    }
  }
}
