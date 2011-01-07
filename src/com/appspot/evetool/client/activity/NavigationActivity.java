package com.appspot.evetool.client.activity;

import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.view.ship.NavigationView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 6:02 PM
 */
public class NavigationActivity extends AbstractActivity {
  private NavigationView navigationView;
  private NavigationPlace place;

  public NavigationActivity(NavigationPlace place, NavigationView navigationView) {
    this.navigationView = navigationView;
    this.place = place;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    navigationView.updateForPlace(place);
  }
}
