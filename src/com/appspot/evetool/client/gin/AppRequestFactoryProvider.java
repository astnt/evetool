package com.appspot.evetool.client.gin;

import com.appspot.evetool.shared.AppRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:27 PM
 */
public class AppRequestFactoryProvider implements Provider<AppRequestFactory> {
  private SimpleEventBus simpleEventBus;

  @Inject
  public AppRequestFactoryProvider(SimpleEventBus simpleEventBus) {
    this.simpleEventBus = simpleEventBus;
  }

  @Override
  public AppRequestFactory get() {
    AppRequestFactory requestFactory = GWT.create(AppRequestFactory.class);
    requestFactory.initialize(simpleEventBus);
    return requestFactory;
  }
}
