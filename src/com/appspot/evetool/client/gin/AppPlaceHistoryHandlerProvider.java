package com.appspot.evetool.client.gin;

import com.appspot.evetool.client.factory.TokenizerFactory;
import com.appspot.evetool.client.mapper.AppPlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:27 PM
 */
public class AppPlaceHistoryHandlerProvider implements Provider<AppPlaceHistoryHandler> {

  private AppPlaceHistoryMapper historyMapper;

  @Inject
  public AppPlaceHistoryHandlerProvider(AppPlaceHistoryMapper historyMapper) {
    this.historyMapper = historyMapper;
  }

  @Override
  public AppPlaceHistoryHandler get() {
    historyMapper.setFactory(new TokenizerFactory());
    return new AppPlaceHistoryHandler(historyMapper);
  }
}
