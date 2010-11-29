package com.appspot.evetool.client.rpc;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 11:02:27 AM
 */
@RemoteServiceRelativePath("shipService")
public interface ShipService extends RemoteService {
  List<ShipProxy> getShips(String[] query);
  Map<String, List<ShipProxy>> getShipsMapByType(String[] query);
}
