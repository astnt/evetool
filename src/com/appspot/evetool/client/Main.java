package com.appspot.evetool.client;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.client.rpc.ShipServiceAsync;
import com.appspot.evetool.client.view.ship.ShipNavigationView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.List;

public class Main implements EntryPoint {
  private ShipServiceAsync shipService = GWT.create(ShipService.class);

  @Override
  public void onModuleLoad() {
    final RootPanel panel = RootPanel.get();
    ShipNavigationView shipNavigationView = new ShipNavigationView();
    panel.add(shipNavigationView);
  }

  private void sample1(final RootPanel panel) {
    shipService.getShips(new String[0], new AsyncCallback<List<ShipProxy>>() {
      @Override
      public void onFailure(Throwable throwable) {
      }
      @Override
      public void onSuccess(List<ShipProxy> shipProxies) {
        List<String> names = new ArrayList<String>();
        for (ShipProxy shipProxy : shipProxies) {
          panel.add(new Label(shipProxy.getName()));
          names.add(shipProxy.getName());
        }
        ShipNavigationView shipNavigationView = new ShipNavigationView(names.toArray(new String[names.size()]));
        panel.add(shipNavigationView);
      }
    });
  }
}