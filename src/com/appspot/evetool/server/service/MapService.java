package com.appspot.evetool.server.service;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.appspot.evetool.server.model.Ship;
import com.google.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 13, 2010
 * Time: 2:24:40 PM
 */
@Singleton
public class MapService {

  public ShipProxy toShipProxy(Ship ship) {
    return new ShipProxy(ship.getGameId(), ship.getName());
  }
}
