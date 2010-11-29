package com.appspot.evetool.servlet;

import com.appspot.evetool.factory.PMF;
import com.appspot.evetool.model.Ship;
import com.google.inject.Singleton;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Feb 28, 2010
 * Time: 1:12:28 PM
 */
@Singleton
public class GuiceTestServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    resp.getWriter().println("Ships list");
    PersistenceManager pm = PMF.get().getPersistenceManager();
    List<Ship> ships = (List<Ship>) pm.newQuery("select from " + Ship.class.getName()).execute();
    resp.getWriter().println("<table border='0'>");
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (Ship ship : ships) {
      resp.getWriter().println(
          String.format("<tr><td><img src='/images/ship?gameId=%s'/></td><td>%s %s - %s</td></tr>",
              ship.getGameId(), ship.getKey(), ship.getGameId(), ship.getName()));
      if (!map.containsKey(ship.getType())) {
        map.put(ship.getType(), 0);
      }
    }
    resp.getWriter().println("</table>");
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      resp.getWriter().append("\"").append(entry.getKey()).append("\", ");
    }

  }
}
