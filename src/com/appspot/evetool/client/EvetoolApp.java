package com.appspot.evetool.client;

import com.appspot.evetool.client.gin.AppActivityManager;
import com.appspot.evetool.client.gin.AppPlaceController;
import com.appspot.evetool.client.gin.AppPlaceHistoryHandler;
import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.view.AppView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:34 PM
 */
public class EvetoolApp {
  private static final Logger log = Logger.getLogger(EvetoolApp.class.getName());
  private AppActivityManager activityManager;
  private AppView appView;
  private AppPlaceHistoryHandler placeHistoryHandler;
  private AppPlaceController placeController;
  private SimpleEventBus eventBus;

  @Inject
  public EvetoolApp(AppActivityManager activityManager, AppView appView,
                    AppPlaceHistoryHandler placeHistoryHandler, AppPlaceController placeController,
                    SimpleEventBus eventBus) {
    this.activityManager = activityManager;
    this.appView = appView;
    this.placeHistoryHandler = placeHistoryHandler;
    this.placeController = placeController;
    this.eventBus = eventBus;
  }


  public void run(HasWidgets root) {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      public void onUncaughtException(Throwable e) {
        log.log(Level.SEVERE, e.getMessage(), e);
      }
    });

    activityManager.setDisplay(appView.getContent());

    placeHistoryHandler.register(placeController, eventBus, new NavigationPlace(null, null));
    placeHistoryHandler.handleCurrentHistory();

    root.add(appView);
  }
}
