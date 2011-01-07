package com.appspot.evetool.server.servlet.rpc;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.server.dao.ShipDao;
import com.appspot.evetool.server.factory.PMF;
import com.appspot.evetool.server.model.Ship;
import com.appspot.evetool.server.service.MapService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileUpload;

import javax.jdo.PersistenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  private MapService mapService;

  @Inject
  public ShipServiceImpl(ShipDao shipDao, MapService mapService) {
    this.shipDao = shipDao;
    this.mapService = mapService;
  }

  @Override
  public Map<String, List<ShipProxy>> getShipsMapByType(String[] query) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    List<Ship> ships = null;
    Map<String, List<ShipProxy>> map = new HashMap<String, List<ShipProxy>>();
    try {
      ships = filterByQuery(pm, query);
      if (ships != null) {
        for (Ship ship : ships) {
          if (!map.containsKey(ship.getType())) {
            map.put(ship.getType(), new ArrayList<ShipProxy>());
          }
          map.get(ship.getType()).add(mapService.toShipProxy(ship));
        }
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, "Ship dao error", e);
    } finally {
      pm.close();
    }
    return map;
  }

  @Override
  public List<ShipProxy> getShips(String[] query) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    List<ShipProxy> shipProxies = new ArrayList<ShipProxy>();
    try {
      List<Ship> ships = filterByQuery(pm, query);
      for (Ship ship : ships) {
        shipProxies.add(mapService.toShipProxy(ship));
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, "Ship dao error", e);
    } finally {
      pm.close();
    }
    return shipProxies;
  }

  private List<Ship> filterByQuery(PersistenceManager pm, String[] query) {
    List<Ship> ships;
    if (query.length > 0) {
      ships = shipDao.getShipByRace(pm, query[0]);
    } else {
      ships = shipDao.getAll(pm);
    }
    return ships;
  }
}
