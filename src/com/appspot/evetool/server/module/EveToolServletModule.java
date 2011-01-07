package com.appspot.evetool.server.module;

import com.appspot.evetool.server.filter.PersistenceFilter;
import com.appspot.evetool.server.servlet.*;
import com.appspot.evetool.server.servlet.images.ShipImagesServlet;
import com.appspot.evetool.server.servlet.rpc.ShipServiceImpl;
import com.appspot.evetool.server.servlet.upload.ShipsUploadServlet;
import com.google.inject.servlet.ServletModule;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 28, 2010
 * Time: 11:57:02 AM
 */
public class EveToolServletModule extends ServletModule {
  @Override protected void configureServlets() {
    filter("*").through(PersistenceFilter.class);
    serve("/login").with(LoginServlet.class);
    serve("/status").with(StatusServlet.class);
    serve("/guicetest").with(GuiceTestServlet.class);
    serve("/upload/ships").with(ShipsUploadServlet.class);
    serve("/test/add").with(TestActionServlet.class);
    serve("/images/ship*").with(ShipImagesServlet.class);
    serve("/edit/ship*").with(ShipServlet.class);
    serve("/evetool/shipService").with(ShipServiceImpl.class);
    serve("/gwtRequest").with(WrapRequestFactoryServlet.class);
  }
}
