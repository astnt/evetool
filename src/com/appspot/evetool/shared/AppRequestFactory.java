package com.appspot.evetool.shared;

import com.google.gwt.requestfactory.shared.RequestFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:30 PM
 */
public interface AppRequestFactory extends RequestFactory {
  ShipRequest shipRequest();
  OrderRequest orderRequest();
}
