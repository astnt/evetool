package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.proxy.ShipProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 13, 2010
 * Time: 3:01:32 PM
 */
public class ShipNavigationTypeView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipNavigationTypeView> {}
  private static MyUiBinder binder = GWT.create(MyUiBinder.class);

  @UiField UListElement list;
  @UiField ParagraphElement header;

  public ShipNavigationTypeView(String name, List<ShipProxy> ships) {
    initWidget(binder.createAndBindUi(this));
    header.setInnerText(name);
    if (ships != null) {
      for (ShipProxy ship : ships) {
        Element li = list.appendChild(list.getOwnerDocument().createElement("li"));
        li.appendChild(new ShipNavigationItemView(ship).getElement());
      }
    }
  }
}
