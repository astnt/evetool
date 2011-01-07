package com.appspot.evetool.server.filter;

import com.appspot.evetool.server.factory.PMF;
import com.google.inject.Singleton;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 8:43 PM
 */
@Singleton
public class PersistenceFilter implements Filter {
  private static final transient Logger log = Logger.getLogger(PersistenceFilter.class.getName());
  private static final PersistenceManagerFactory persistenceManagerFactory = PMF.get();

  private static PersistenceManagerFactory factory() {
    return persistenceManagerFactory;
  }

  private static ThreadLocal<PersistenceManager> currentManager =
      new ThreadLocal<PersistenceManager>();

  public static PersistenceManager getManager() {
    if (currentManager.get() == null) {
      final PersistenceManager pm = factory().getPersistenceManager();
      currentManager.set(pm);
    }
    return currentManager.get();
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
                       FilterChain chain) throws IOException, ServletException {
    try {
      chain.doFilter(req, res);
    } finally {
      PersistenceManager manager = currentManager.get();
      if (manager != null) {
        if (!manager.isClosed()) {
          manager.flush();
          manager.close();
        }
      }
      currentManager.set(null);
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  @Override
  public void destroy() {
  }
}
