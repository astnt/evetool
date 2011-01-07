package com.appspot.evetool.client.mapper;

import com.appspot.evetool.client.activity.NavigationActivity;
import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.view.ship.NavigationView;
import com.appspot.evetool.client.view.ship.ShipDetailsView;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:33 PM
 */
public class AppActivityMapper implements ActivityMapper {
  private NavigationView navigationView;
  private ShipDetailsView shipDetailsView;

  @Inject
  public AppActivityMapper(NavigationView navigationView, ShipDetailsView shipDetailsView) {
    this.navigationView = navigationView;
    this.shipDetailsView = shipDetailsView;
  }

  @Override
  public Activity getActivity(Place place) {
    if (place instanceof NavigationPlace) {
      return new NavigationActivity((NavigationPlace) place, navigationView, shipDetailsView);
    }
    return null;
  }
}
