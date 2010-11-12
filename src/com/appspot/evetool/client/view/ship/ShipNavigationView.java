package com.appspot.evetool.client.view.ship;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DListElement;
import com.google.gwt.dom.client.Node;
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

  public ShipNavigationView(String... names) {
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
            element.addClassName(style.selected());
            Node next = element.getNextSibling();
            while (next.getNodeType() == Element.TEXT_NODE) {
              next = next.getNextSibling();
            }
            ((Element)next).addClassName(style.selected());
          }
        }
      });
    }
  }

  private void removeSelected() {
    for (int i = 0; i < tabs.getChildCount(); i++) {
      Element element = (Element) tabs.getChildNodes().getItem(i);
      if (element.getNodeType() == Element.TEXT_NODE) { continue; }
      element.removeClassName(style.selected());
    }
  }}
