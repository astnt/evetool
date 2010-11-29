package com.appspot.evetool.client.rpc;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;
import java.util.Map;

public interface ShipServiceAsync {
  void getShips(String[] query, AsyncCallback<List<ShipProxy>> async);
  void getShipsMapByType(String[] query, AsyncCallback<Map<String, List<ShipProxy>>> async);
}
