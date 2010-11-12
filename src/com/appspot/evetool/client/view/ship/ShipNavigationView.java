package com.appspot.evetool.client.view.ship;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 12:22:44 PM
 */
public class ShipNavigationView extends Composite {
  interface MyUiBinder extends UiBinder<Widget, ShipNavigationView> {}
  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField ListBox listBox;

  public ShipNavigationView(String... names) {
    initWidget(uiBinder.createAndBindUi(this));
    for (String name : names) {
      listBox.addItem(name);
    }
  }
}
