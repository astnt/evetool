package com.appspot.evetool.server.servlet;

import com.appspot.evetool.server.model.Order;
import com.appspot.evetool.server.service.EveCentralService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 8, 2010
 * Time: 5:21:04 PM
 */
@Singleton
public class TestActionServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(TestActionServlet.class.getName());
  EveCentralService eveCentralService;

  @Inject
  public TestActionServlet(EveCentralService eveCentralService) {
    this.eveCentralService = eveCentralService;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("utf8");
    resp.setContentType("text/plain");
    try {
      List<Order> orders = eveCentralService.fetchQuickLook("34");
      for (Order order : orders) {
        resp.getWriter().println(order.getId());
      }
    } catch (SAXException e) {
      log.log(Level.SEVERE, "sax error", e);
    } catch (ParserConfigurationException e) {
      log.log(Level.SEVERE, "parser error", e);
    } catch (XPathExpressionException e) {
      log.log(Level.SEVERE, "xpath error", e);
    }
  }
}
