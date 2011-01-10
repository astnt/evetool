package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.gin.AppPlaceController;
import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.client.proxy.ShipProxy;
import com.appspot.evetool.client.rpc.ShipService;
import com.appspot.evetool.client.rpc.ShipServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DListElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 12:22:44 PM
 */
public class NavigationView extends Composite {
//  private static final String[] SHIP_TYPES = new String[]{ "Battleship", "Cruiser", "Frigate" };
  private static final String[] SHIP_TYPES = new String[]{
      "Frigate", "Cruiser", "Assault Ship",
      "Titan", "Battlecruiser", "Mothership", "Black Ops", "Destroyer", "Freighter",
      "Industrial", "Transport Ship", "Battleship", "Strategic Cruiser", "Interdictor", "Dreadnought",
      "Logistics", "Interceptor", "Command Ship", "Carrier", "Heavy Assault Ship", "Stealth Bomber", "Covert Ops",
      "Marauder", "Heavy Interdictor", "Jump Freighter"
      , "Combat Recon Ship", "Force Recon Ship", "Shuttle", "Electronic Attack Ship",
      // "Capital Industrial Ship", "Industrial Command Ship", "Exhumer", "Mining Barge", 
  };
  private String ship;

  public void updateForPlace(NavigationPlace place) {
    String race = place.getRace();
    removeSelected();
    if (race != null) {
      Element element = raceContent.get(race);
      Element content = selectElementsAndReturnLast(element);
      if (!raceLoaded.containsKey(race)) {
        loadShips(race, content);
        raceLoaded.put(race, true);
      }
    }
    ship = place.getShip();
    updateSelectedShip();
  }

  private void updateSelectedShip() {
    if (ship != null && ship.length() > 0 && shipItems.containsKey(ship)) {
      if (selected != null) { selected.setSelected(false); }
      selected = shipItems.get(ship);
      selected.setSelected(true);
    }
  }

  interface MyUiBinder extends UiBinder<Widget, NavigationView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);
  public interface MyStyle extends CssResource {
    String selected();
    String label();
    String disabled();
    String clear();
    String content();
    @ClassName("tab-content")
    String tabContent();
    String tab();
    @ClassName("content-leaf")
    String contentLeaf();
    String textEdit();
    String large();
    String tabs();
  }
  @UiField MyStyle style;
  @UiField DListElement tabs;

  private Map<String, Element> raceContent = new HashMap<String, Element>();
  private Map<String, Boolean> raceLoaded = new HashMap<String, Boolean>();
  private Map<String, ShipItemView> shipItems = new HashMap<String, ShipItemView>();
  private ShipItemView selected;

  interface Resources extends ClientBundle {
    @Source("icon19_01.png") ImageResource caldari();
    @Source("icon19_02.png") ImageResource minmatar();
    @Source("icon19_03.png") ImageResource gallente();
    @Source("icon19_04.png") ImageResource amarr();
  }

  private ShipServiceAsync shipService = GWT.create(ShipService.class);
  private AppPlaceController placeController;

  @Inject
  public NavigationView(final AppPlaceController placeController) {
    this.placeController = placeController;
    initWidget(binder.createAndBindUi(this));
    for (int i = 0; i < tabs.getChildCount(); i++) {
      Node node = tabs.getChildNodes().getItem(i);
      final Element element = (Element) node;
      if ("DD".equals(element.getTagName())) { continue; }
      if ("DT".equals(element.getTagName())) {
        final String race = element.getAttribute("id");
        DOM.sinkEvents(element, Event.ONCLICK);
        raceContent.put(race, element);
        DOM.setEventListener(element, new EventListener() {
          @Override
          public void onBrowserEvent(Event event) {
            if ("click".equals(event.getType())) {
              placeController.goTo(new NavigationPlace(race, null));
            }
          }
        });
      }
    }
  }

  private void loadShips(final String race, final Element content) {
    shipService.getShipsMapByType(new String[]{ race }, new AsyncCallback<Map<String, List<ShipProxy>>>() {
      @Override
      public void onFailure(Throwable throwable) {
        GWT.log("error", null);
      }

      @Override
      public void onSuccess(Map<String, List<ShipProxy>> shipsByType) {
        Element nextElement = findNextElement(content.getFirstChild());
        removeChild(nextElement);
        for (String type : SHIP_TYPES) {
          List<ShipProxy> shipProxies = shipsByType.get(type);
          ShipTypeView shipTypeView = new ShipTypeView(race, type, shipProxies, placeController);
          nextElement.appendChild(shipTypeView.getElement());
          for (ShipItemView item : shipTypeView.getItems()) {
            shipItems.put(item.getShip().getName(), item);
          }
        }
        com.google.gwt.dom.client.Element br = content.getOwnerDocument().createElement("br");
        br.setClassName(style.clear());
        nextElement.appendChild(br);
        updateSelectedShip();
      }
    });
  }

  private void removeChild(Element element) {
    while (element.getFirstChild() != null) { element.removeChild(element.getFirstChild()); }
  }

  private Element selectElementsAndReturnLast(Element element) {
    element.addClassName(style.selected());
    Element content = findNextElement(element);
    content.addClassName(style.selected());
    return content;
  }

  private Element findNextElement(Node element) {
    Node next = element.getNextSibling();
    while (next.getNodeType() == Element.TEXT_NODE) {
      next = next.getNextSibling();
    }
    return (Element) next;
  }

  private void removeSelected() {
    for (int i = 0; i < tabs.getChildCount(); i++) {
      Element element = (Element) tabs.getChildNodes().getItem(i);
      if (element.getNodeType() == Element.TEXT_NODE) { continue; }
      element.removeClassName(style.selected());
    }
  }
}
