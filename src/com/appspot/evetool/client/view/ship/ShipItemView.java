package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.gin.AppPlaceController;
import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.proxy.ShipProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 2:57:22 PM
 */
public class ShipItemView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipItemView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);
  @UiField ImageElement icon;
  @UiField DivElement name;
  public interface Style extends CssResource {
    String selected();
    String item();
  }
  @UiField Style style;

  private ShipProxy ship;

  public ShipItemView(final String race, final ShipProxy ship, final AppPlaceController placeController) {
    this.ship = ship;
    initWidget(binder.createAndBindUi(this));
    icon.setSrc("/images/ship?gameId=" + ship.getGameId());
    name.setInnerText(ship.getName());
    Element element = getElement();
    DOM.sinkEvents(element, Event.ONCLICK);
    DOM.setEventListener(element, new EventListener() {
      @Override
      public void onBrowserEvent(Event event) {
        if ("click".equals(event.getType())) {
          placeController.goTo(new NavigationPlace(race, ship.getName()));
        }
      }
    });
  }

  public void setSelected(boolean selected) {
    if (selected) {
      addStyleName(style.selected());
    } else {
      removeStyleName(style.selected());
    }
  }

  public ShipProxy getShip() {
    return ship;
  }
}
