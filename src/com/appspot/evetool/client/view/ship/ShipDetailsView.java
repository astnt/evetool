package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.shared.AppRequestFactory;
import com.appspot.evetool.shared.ShipProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 6:59 PM
 */
public class ShipDetailsView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipDetailsView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);

  @UiField HeadingElement name;
  @UiField SpanElement type;

  private AppRequestFactory requestFactory;

  @Inject
  public ShipDetailsView(AppRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
    initWidget(binder.createAndBindUi(this));
  }

  public void updateForPlace(NavigationPlace place) {
    name.setInnerText(place.getShip());
    requestFactory.shipRequest().findShipByName(place.getShip()).fire(new Receiver<ShipProxy>() {
      @Override
      public void onSuccess(ShipProxy ship) {
        type.setInnerText(ship.getType());
      }
    });
  }
}
