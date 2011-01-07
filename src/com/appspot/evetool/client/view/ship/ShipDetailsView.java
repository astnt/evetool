package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.place.NavigationPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 6:59 PM
 */
public class ShipDetailsView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipDetailsView> {}

  private static MyUiBinder binder = GWT.create(MyUiBinder.class);
  public ShipDetailsView() {
    initWidget(binder.createAndBindUi(this));
  }

  @UiField HeadingElement name;

  public void updateForPlace(NavigationPlace place) {
    name.setInnerText(place.getShip());
  }
}
