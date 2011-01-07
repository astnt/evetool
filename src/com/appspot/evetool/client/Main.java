package com.appspot.evetool.client;

import com.appspot.evetool.client.gin.AppGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {
  @Override
  public void onModuleLoad() {
    AppGinjector injector = GWT.create(AppGinjector.class);
    injector.getEvetoolApp().run(RootPanel.get());
  }
}