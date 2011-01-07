package com.appspot.evetool.server.factory;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 8, 2010
 * Time: 5:18:47 PM
 */
public final class PMF {
  private static final PersistenceManagerFactory pmfInstance =
      JDOHelper.getPersistenceManagerFactory("transactions-optional");

  private PMF() {
  }

  public static PersistenceManagerFactory get() {
    return pmfInstance;
  }
}
