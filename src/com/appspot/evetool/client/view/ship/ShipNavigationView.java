package com.appspot.evetool.client.view.ship;

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

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 12:22:44 PM
 */
public class ShipNavigationView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipNavigationView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);
  public interface MyStyle extends CssResource {
    String selected();
    String label();
    String disabled();
  }
  @UiField MyStyle style;
  @UiField DListElement tabs;

  interface Resources extends ClientBundle {
    @Source("amarr.png")
    ImageResource amarr();
    @Source("caldari.png")
    ImageResource caldari();
    @Source("gallente.png")
    ImageResource gallente();
    @Source("minmatar.png")
    ImageResource minmatar();
  }

  private ShipServiceAsync shipService = GWT.create(ShipService.class);

  public ShipNavigationView() {
    initWidget(binder.createAndBindUi(this));
    for (int i = 0; i < tabs.getChildCount(); i++) {
      Node node = tabs.getChildNodes().getItem(i);
      final Element element = (Element) node;
      if ("DD".equals(element.getTagName())) { continue; }
      DOM.sinkEvents(element, Event.ONCLICK);
      DOM.setEventListener(element, new EventListener() {
        @Override
        public void onBrowserEvent(Event event) {
          if ("click".equals(event.getType())) {
            removeSelected();
            Element content = addSelect(element);
            loadShips(element.getAttribute("id"), content);
          }
        }
      });
    }
  }

  private void loadShips(String race, final Element content) {
    shipService.getShips(new String[0], new AsyncCallback<List<ShipProxy>>() {
      @Override
      public void onFailure(Throwable throwable) {
        GWT.log("error", null);
      }
      @Override
      public void onSuccess(List<ShipProxy> shipProxies) {
        for (ShipProxy shipProxy : shipProxies) {
          Element nextElement = findNextElement(content.getFirstChild());
          removeChild(nextElement);
//          nextElement.appendChild(content.getOwnerDocument().createTextNode(shipProxy.getName()));
          nextElement.appendChild(new ShipNavigationItemView(shipProxy).getElement());
        }
      }
    });
  }

  private void removeChild(Element element) {
    while (element.getFirstChild() != null) { element.removeChild(element.getFirstChild()); }
  }

  private Element addSelect(Element element) {
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
  }}
