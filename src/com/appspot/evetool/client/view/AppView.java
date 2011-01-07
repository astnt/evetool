package com.appspot.evetool.client.view;

import com.appspot.evetool.client.view.ship.NavigationView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 4:48 PM
 */
public class AppView extends Composite {
  interface Binder extends UiBinder<Widget, AppView> {}
  private static Binder binder = GWT.create(Binder.class);
  public interface Style extends CssResource {
    String footer();
  }
  @UiField Style style;

  @UiField SimplePanel content;
  @UiField DivElement footer;
  @UiField(provided = true) NavigationView shipNavigation;

  @Inject
  public AppView(NavigationView shipNavigation) {
    this.shipNavigation = shipNavigation;
    initWidget(binder.createAndBindUi(this));
    footer.addClassName(style.footer());
  }

  public SimplePanel getContent() {
    return content;
  }
}
