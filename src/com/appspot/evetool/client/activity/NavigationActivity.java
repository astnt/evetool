package com.appspot.evetool.client.activity;

import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.view.ship.NavigationView;
import com.appspot.evetool.client.view.ship.ShipDetailsView;
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
  private ShipDetailsView shipDetailsView;

  public NavigationActivity(NavigationPlace place, NavigationView navigationView, ShipDetailsView shipDetailsView) {
    this.navigationView = navigationView;
    this.place = place;
    this.shipDetailsView = shipDetailsView;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    navigationView.updateForPlace(place);
    shipDetailsView.updateForPlace(place);
    panel.setWidget(shipDetailsView);
  }
}
