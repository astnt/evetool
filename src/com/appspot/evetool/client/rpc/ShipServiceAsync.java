package com.appspot.evetool.client.rpc;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface ShipServiceAsync {
  void getShips(String[] query, AsyncCallback<List<ShipProxy>> async);
}
