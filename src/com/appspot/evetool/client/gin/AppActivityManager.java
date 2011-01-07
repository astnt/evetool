package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.mapper.AppActivityMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:32 PM
 */
public class AppActivityManager extends ActivityManager {
  @Inject
  public AppActivityManager(AppActivityMapper mapper, SimpleEventBus eventBus) {
    super(mapper, eventBus);
  }
}
