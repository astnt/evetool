package com.appspot.evetool.client.event;

import com.google.gwt.event.shared.HandlerManager;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:29 PM
 */
public class EventBus {
  public static final HandlerManager INSTANCE = new HandlerManager(null);

  private EventBus() {}
}
