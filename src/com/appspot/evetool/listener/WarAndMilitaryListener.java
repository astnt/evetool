package com.appspot.evetool.listener;

import com.appspot.evetool.module.EvetoolServletModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 28, 2010
 * Time: 11:54:50 AM
 */
public class WarAndMilitaryListener extends GuiceServletContextListener {
  @Override protected Injector getInjector() {
    return Guice.createInjector(
        new EvetoolServletModule()
    );
  }
}
