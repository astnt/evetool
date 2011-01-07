package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.EvetoolApp;
import com.appspot.evetool.client.mapper.AppActivityMapper;
import com.appspot.evetool.client.view.AppView;
import com.appspot.evetool.client.view.ship.NavigationView;
import com.appspot.evetool.shared.AppRequestFactory;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:23 PM
 */
public class AppModule extends AbstractGinModule {
  protected void configure() {
    bind(SimpleEventBus.class).in(Singleton.class);
    bind(AppPlaceHistoryHandlerProvider.class).in(Singleton.class);
    bind(AppRequestFactoryProvider.class).in(Singleton.class);
    bind(HandlerManager.class).toProvider(HandlerManagerProvider.class).in(Singleton.class);
    bind(AppRequestFactory.class).toProvider(AppRequestFactoryProvider.class).in(Singleton.class);
    bind(AppPlaceController.class).in(Singleton.class);
    bind(AppPlaceHistoryHandler.class).toProvider(AppPlaceHistoryHandlerProvider.class).in(Singleton.class);
    bind(AppActivityManager.class).in(Singleton.class);
    bind(AppActivityMapper.class).in(Singleton.class);
    bind(EvetoolApp.class).in(Singleton.class);
    bind(AppView.class).in(Singleton.class);
    bind(NavigationView.class).in(Singleton.class);
  }
}
