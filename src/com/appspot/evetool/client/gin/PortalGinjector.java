package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.EvetoolApp;
import com.appspot.evetool.client.factory.TokenizerFactory;
import com.appspot.evetool.client.mapper.AppPlaceHistoryMapper;
import com.appspot.evetool.shared.AppRequestFactory;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:22 PM
 */
@GinModules(PortalModule.class)
public interface PortalGinjector extends Ginjector {
  HandlerManager getEventHandler();
  SimpleEventBus getSimpleEventBus();
  AppRequestFactory getAppRequestFactory();
  AppPlaceHistoryMapper getAppPlaceHistoryMapper();
  AppPlaceController getAppPlaceController();
  AppPlaceHistoryHandler getAppPlaceHistoryHandler();
  EvetoolApp getEvetoolApp();
  TokenizerFactory getTokenizerFactory();
}
