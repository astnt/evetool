package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.event.EventBus;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:28 PM
 */
public class HandlerManagerProvider implements Provider<HandlerManager> {
  public HandlerManager get() {
    return EventBus.INSTANCE;
  }
}
