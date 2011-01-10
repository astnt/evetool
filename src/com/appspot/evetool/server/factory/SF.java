package com.appspot.evetool.server.factory;

import com.appspot.evetool.server.service.EveCentralService;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/10/11
 * Time: 2:51 PM
 */
public class SF {
  private static EveCentralService eveCentralService = new EveCentralService();

  public static EveCentralService getEveCentralService() {
    return eveCentralService;
  }
}
