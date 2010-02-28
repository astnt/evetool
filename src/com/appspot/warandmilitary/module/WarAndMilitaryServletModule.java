package com.appspot.warandmilitary.module;

import com.appspot.warandmilitary.LoginServlet;
import com.appspot.warandmilitary.StatusServlet;
import com.appspot.warandmilitary.servlet.GuiceTestServlet;
import com.google.inject.servlet.ServletModule;
import org.example.HelloAppEngineServlet;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 28, 2010
 * Time: 11:57:02 AM
 */
public class WarAndMilitaryServletModule extends ServletModule {
  @Override protected void configureServlets() {
    serve("/login").with(LoginServlet.class);
    serve("/status").with(StatusServlet.class);
    serve("/helloappengine").with(HelloAppEngineServlet.class);
    serve("/guicetest").with(GuiceTestServlet.class);
  }
}
