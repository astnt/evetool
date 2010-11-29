package com.appspot.evetool.client;

import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.client.rpc.ShipServiceAsync;
import com.appspot.evetool.client.view.ship.ShipNavigationView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {
  private ShipServiceAsync shipService = GWT.create(ShipService.class);

  @Override
  public void onModuleLoad() {
    final RootPanel panel = RootPanel.get();
    final ShipNavigationView shipNavigationView = new ShipNavigationView();
    panel.add(shipNavigationView);

    //
    History.addValueChangeHandler(new ValueChangeHandler<String>() {
      public void onValueChange(ValueChangeEvent<String> event) {
        String historyToken = event.getValue();
      }
    });

    String historyToken = History.getToken();
    if (historyToken != null && historyToken.length() > 0) {
      shipNavigationView.doClick(historyToken);
    }
  }

}