package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.mapper.AppPlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:32 PM
 */
public class AppPlaceHistoryHandler extends PlaceHistoryHandler {
  @Inject
  public AppPlaceHistoryHandler(AppPlaceHistoryMapper mapper) {
    super(mapper);
  }
}
