package com.appspot.evetool.client;

import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.client.rpc.ShipServiceAsync;
import com.appspot.evetool.client.view.ship.ShipNavigationView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {
  private ShipServiceAsync shipService = GWT.create(ShipService.class);

  @Override
  public void onModuleLoad() {
    final RootPanel panel = RootPanel.get();
    ShipNavigationView shipNavigationView = new ShipNavigationView();
    panel.add(shipNavigationView);
  }

}