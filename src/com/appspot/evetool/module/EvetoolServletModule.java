package com.appspot.evetool.module;

import com.appspot.evetool.LoginServlet;
import com.appspot.evetool.StatusServlet;
import com.appspot.evetool.servlet.GuiceTestServlet;
import com.appspot.evetool.servlet.ShipServlet;
import com.appspot.evetool.servlet.TestActionServlet;
import com.appspot.evetool.servlet.images.ShipImagesServlet;
import com.appspot.evetool.servlet.rpc.StockPriceServiceImpl;
import com.appspot.evetool.upload.ShipsUploadServlet;
import com.google.inject.servlet.ServletModule;
import org.example.HelloAppEngineServlet;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 28, 2010
 * Time: 11:57:02 AM
 */
public class EvetoolServletModule extends ServletModule {
  @Override protected void configureServlets() {
    serve("/login").with(LoginServlet.class);
    serve("/status").with(StatusServlet.class);
    serve("/helloappengine").with(HelloAppEngineServlet.class);
    serve("/guicetest").with(GuiceTestServlet.class);
    serve("/upload/ships").with(ShipsUploadServlet.class);
    serve("/test/add").with(TestActionServlet.class);
    serve("/images/ship*").with(ShipImagesServlet.class);
    serve("/edit/ship*").with(ShipServlet.class);
    serve("/evetool/shipService").with(StockPriceServiceImpl.class);
  }
}
