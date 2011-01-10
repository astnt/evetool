package com.appspot.evetool.shared;

import com.appspot.evetool.server.model.Order;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/10/11
 * Time: 2:46 PM
 */
@ProxyFor(Order.class)
public interface OrderProxy extends EntityProxy {
  EntityProxyId<OrderProxy> stableId();
  String getId();
  String getStationName();
  String getSecurity();
  String getRange();
  String getPrice();
  String getVolRemain();
  String getMinVolume();
  String getExpires();
  String getReportedTime();
  String getType();
}
