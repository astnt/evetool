package com.appspot.evetool.shared;

import com.appspot.evetool.server.model.Ship;
import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 8:40 PM
 */
@Service(Ship.class)
public interface ShipRequest extends RequestContext {
  Request<List<ShipProxy>> findAllShips();
  Request<ShipProxy> findShip(String id);
  Request<ShipProxy> findShipByName(String name);
  InstanceRequest<ShipProxy, Void> persist();
  InstanceRequest<ShipProxy, Void> remove();
}
