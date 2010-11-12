package com.appspot.evetool.servlet.rpc;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.dao.ShipDao;
import com.appspot.evetool.factory.PMF;
import com.appspot.evetool.model.Ship;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileUpload;

import javax.jdo.PersistenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 11:05:53 AM
 */
@SuppressWarnings({"GwtServiceNotRegistered"})
@Singleton
public class ShipServiceImpl extends RemoteServiceServlet implements ShipService {
  private static final Logger log = Logger.getLogger(FileUpload.class.getName());
  private ShipDao shipDao;

  @Inject
  public ShipServiceImpl(ShipDao shipDao) {
    this.shipDao = shipDao;
  }

  @Override
  public List<ShipProxy> getShips(String[] query) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    List<ShipProxy> shipProxies = new ArrayList<ShipProxy>();
    try {
      for (Ship ship : shipDao.getAll(pm)) {
        shipProxies.add(new ShipProxy(ship.getGameId(), ship.getName()));
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, "Ship dao error", e);
    } finally {
      pm.close();
    }
    return shipProxies;
  }
}
