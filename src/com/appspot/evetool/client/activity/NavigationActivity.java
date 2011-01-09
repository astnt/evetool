package com.appspot.evetool.client.activity;

import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.view.ship.NavigationView;
import com.appspot.evetool.client.view.ship.ShipDetailsView;
import com.appspot.evetool.client.view.ship.UnselectedView;
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
  private UnselectedView unselectedView;

  public NavigationActivity(NavigationPlace place, NavigationView navigationView,
                            ShipDetailsView shipDetailsView, UnselectedView unselectedView) {
    this.navigationView = navigationView;
    this.place = place;
    this.shipDetailsView = shipDetailsView;
    this.unselectedView = unselectedView;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    navigationView.updateForPlace(place);
    if (place.getRace() != null && place.getShip() != null) {
      shipDetailsView.updateForPlace(place);
      panel.setWidget(shipDetailsView);
    } else {
      unselectedView.updateForPlace(place);
      panel.setWidget(unselectedView);
    }
  }
}
