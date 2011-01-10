package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.place.NavigationPlace;
import com.appspot.evetool.shared.AppRequestFactory;
import com.appspot.evetool.shared.OrderProxy;
import com.appspot.evetool.shared.ShipProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.text.client.DoubleRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 6:59 PM
 */
public class ShipDetailsView extends Composite {
  Logger logger = Logger.getLogger("ShipDetailsView");
  interface MyUiBinder extends UiBinder<Widget, ShipDetailsView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);
  public interface Style extends CssResource {
    String item();
    String properties();
    String row();
  }
  @UiField Style style;

  @UiField HeadingElement name;
  @UiField ImageElement ship256;
  @UiField HTML description;
  @UiField TableElement properties;
  @UiField Button getPrices;
  @UiField CellTable<OrderProxy> prices;
  @UiField ParagraphElement pricesMsg;

  private AppRequestFactory requestFactory;
  private ShipProxy ship;

  @Inject
  public ShipDetailsView(AppRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
    initWidget(binder.createAndBindUi(this));
    properties.addClassName(style.properties());
    prices.setVisible(false);
    makePrices();
  }

  public void updateForPlace(NavigationPlace place) {
    name.setInnerText(place.getShip());
    description.setHTML("loading...");
    getPrices.setEnabled(false);
    requestFactory.shipRequest().findShipByName(place.getShip()).fire(new Receiver<ShipProxy>() {
      @Override
      public void onSuccess(ShipProxy responseShip) {
        ship = responseShip;
        if (ship != null) {
          description.setHTML(ship.getDescription().replace("\n", "<br/>"));
          ship256.setSrc("/images/ship?gameId=" + ship.getId() + "&type=ship256");
          PropertiesBuilder builder = new PropertiesBuilder();
          builder.add("Base Price", ship.getBasePrice());
          builder.add("Mass", ship.getMass());
          builder.add("Volume", ship.getVolume());
          builder.add("Capacity", ship.getCapacity());
          builder.add("Inertia Modifier", ship.getInertiaModifier());
          properties.setInnerHTML(builder.result());
          getPrices.setEnabled(true);
          prices.setVisible(false);
        }
      }
    });
  }

  @UiHandler("getPrices")
  public void onGetPricesClick(ClickEvent e) {
    requestFactory.orderRequest().findOrdersByType(ship.getId()).fire(new Receiver<List<OrderProxy>>() {
      @Override
      public void onFailure(ServerFailure error) {
        getPrices.setText("Get prices");
        getPrices.setEnabled(true);
        pricesMsg.setInnerText(error.getMessage());
      }

      @Override
      public void onSuccess(List<OrderProxy> orderProxies) {
        prices.setRowCount(orderProxies.size(), true);
        prices.setRowData(0, orderProxies);
        prices.setVisible(true);
        getPrices.setText("Get prices");
        getPrices.setEnabled(true);
        pricesMsg.setInnerText("");
      }
    });
    getPrices.setEnabled(false);
    getPrices.setText("Loading...");
  }

  private class PropertiesBuilder {
    private boolean row = false;
    private String html = "";

    private void add(String name, String value) {
      html += "<tr class=\"" + (row ? style.row() : "") +  "\"><td>" + name + "</td><td>" + value + "</td></tr>";
      row = !row;
    }

    public String result() {
      return html;
    }
  }

  private void makePrices() {
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return "sell_orders".equals(orderProxy.getType()) ? "Sell" : "Buy";
      }
    }, "Type");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return orderProxy.getStationName();
      }
    }, "Station Name");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return orderProxy.getRange();
      }
    }, "Range");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return DoubleRenderer.instance().render(Double.parseDouble(orderProxy.getPrice()));
      }
    }, "Price");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return orderProxy.getVolRemain();
      }
    }, "Remain");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return orderProxy.getExpires();
      }
    }, "Expires");
    prices.addColumn(new TextColumn<OrderProxy>() {
      public String getValue(OrderProxy orderProxy) {
        return orderProxy.getReportedTime();
      }
    }, "Reported Time");
  }
}
