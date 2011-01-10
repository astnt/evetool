package com.appspot.evetool.shared;

import com.appspot.evetool.server.model.Order;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/10/11
 * Time: 2:49 PM
 */
@Service(Order.class)
public interface OrderRequest extends RequestContext {
  Request<OrderProxy> findOrder(String id);
  Request<List<OrderProxy>> findOrdersByType(String typeId);
}
