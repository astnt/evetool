package com.appspot.evetool.shared;

import com.appspot.evetool.server.model.Ship;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 8:37 PM
 */
@ProxyFor(Ship.class)
public interface ShipProxy extends EntityProxy {
  EntityProxyId<ShipProxy> stableId();
  String getId();
  String getName();
  String getType();
  String getDescription();
  String getBasePrice();
  String getMass();
  String getVolume();
  String getCapacity();
  String getInertiaModifier();
}
