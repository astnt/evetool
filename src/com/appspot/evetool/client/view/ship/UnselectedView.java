package com.appspot.evetool.client.view.ship;

import com.appspot.evetool.client.place.NavigationPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/9/11
 * Time: 2:52 PM
 */
public class UnselectedView extends Composite {
  interface Binder extends UiBinder<Widget, UnselectedView> {}
  private static Binder binder = GWT.create(Binder.class);

  @UiField ParagraphElement message;

  public UnselectedView() {
    initWidget(binder.createAndBindUi(this));
  }

  public void updateForPlace(NavigationPlace place) {
    if (place.getRace() == null) {
      message.setInnerText("Select race.");
    } else if (place.getShip() == null) {
      message.setInnerText("Select ship.");
    }
  }
}
