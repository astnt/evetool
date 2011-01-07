package com.appspot.evetool.client.gin;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:31 PM
 */
public class AppPlaceController extends PlaceController {
  @Inject
  public AppPlaceController(SimpleEventBus eventBus) {
    super(eventBus);
  }
}
