package com.appspot.evetool.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 5:22 PM
 * TODO
 */
public class TabPanel extends Composite implements HasWidgets {
  interface Binder extends UiBinder<Widget, TabPanel> {}
  private static Binder binder = GWT.create(Binder.class);

  public TabPanel() {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void add(Widget w) {

  }

  @Override
  public void clear() {

  }

  @Override
  public Iterator<Widget> iterator() {
    return new WidgetIterator();
  }

  @Override
  public boolean remove(Widget w) {
    return false;
  }

  private class WidgetIterator implements Iterator<Widget> {
    @Override
    public boolean hasNext() {
      return false;
    }
    @Override
    public Widget next() {
      return null;
    }
    @Override
    public void remove() {
    }
  }
}
