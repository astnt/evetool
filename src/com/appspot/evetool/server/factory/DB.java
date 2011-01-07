package com.appspot.evetool.server.factory;

import com.appspot.evetool.server.filter.PersistenceFilter;

import javax.jdo.PersistenceManager;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 8:42 PM
 */
public class DB {
  public static PersistenceManager getManager() {
    return PersistenceFilter.getManager();
  }
}
