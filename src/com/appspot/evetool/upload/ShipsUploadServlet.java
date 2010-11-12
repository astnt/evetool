package com.appspot.evetool.upload;

import com.appspot.evetool.dao.ShipDao;
import com.appspot.evetool.factory.PMF;
import com.appspot.evetool.model.Ship;
import com.google.appengine.api.datastore.Blob;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 8, 2010
 * Time: 3:18:51 PM
 */
@Singleton
public class ShipsUploadServlet extends HttpServlet {
  private static final Logger log =
      Logger.getLogger(FileUpload.class.getName());

  private ShipDao shipDao;

  @Inject
  public ShipsUploadServlet(ShipDao shipDao) {
    this.shipDao = shipDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.getWriter().println("ship get");
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/plain");
    String gameId = req.getParameter("gameId");
    String mode = req.getParameter("mode");
    resp.getWriter().println("ship post mode " + mode + ":");
    resp.getWriter().println(String.format("gameId: %s", gameId));
    resp.getWriter().println(String.format("name: %s", req.getParameter("name")));
    resp.getWriter().println(String.format("race: %s", req.getParameter("race")));
    resp.getWriter().println(String.format("type: %s", req.getParameter("type")));

    if (gameId != null && gameId.length() > 0) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      boolean isNew = false;
      Ship ship = shipDao.getShipByGameId(pm, gameId);
      if (ship == null) {
        ship = new Ship(req.getParameter("gameId"), req.getParameter("name"), req.getParameter("type"),
            req.getParameter("race"), getShipIcon(req));
        isNew = true;
      } else { // Update
        if ("file".equals(mode)) {
          ship.setIcon(getShipIcon(req));
        }
      }
      try {
        if (isNew) {
          pm.makePersistent(ship);
        }
      } finally {
        pm.close();
      }
      resp.getWriter().println(isNew ? "Created" : "Updated");
    }

  }

  private Blob getShipIcon(HttpServletRequest req) throws IOException {
    ServletFileUpload upload = new ServletFileUpload();
    try {
      FileItemIterator iterator = upload.getItemIterator(req);
      while (iterator.hasNext()) {
        FileItemStream item = iterator.next();
        InputStream stream = item.openStream();
        if (!item.isFormField() && "shipIcon".equals(item.getFieldName())) {
          return new Blob(IOUtils.toByteArray(stream));
        }
      }

    } catch (FileUploadException e) {
      log.log(Level.SEVERE, "Ship icon error", e);
    }
    return null;
  }





























  private void upload(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/plain");

      ServletFileUpload upload = new ServletFileUpload();
      FileItemIterator iterator = upload.getItemIterator(req);
      while (iterator.hasNext()) {
        FileItemStream item = iterator.next();
        InputStream stream = item.openStream();

        if (item.isFormField()) {
          log.warning("Got a form field: " + item.getFieldName());
        } else {
          log.warning("Got an uploaded file: " + item.getFieldName() +
              ", name = " + item.getName());

          // You now have the filename (item.getName() and the
          // contents (which you can read from stream).  Here we just
          // print them back out to the servlet output stream, but you
          // will probably want to do something more interesting (for
          // example, wrap them in a Blob and commit them to the
          // datastore).
          int len;
          byte[] buffer = new byte[8192];
          while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            res.getOutputStream().write(buffer, 0, len);
          }
        }
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

//    resp.getWriter()
//        .append("<form action=\"/upload/ships\" method=\"post\" enctype=\"multipart/form-data\">\n" +
//        "    <input name=\"name\" type=\"text\" value=\"\"> <br/>\n" +
//        "    <input name=\"imageField\" type=\"file\" size=\"30\"> <br/>\n" +
//        "    <input name=\"Submit\" type=\"submit\" value=\"Sumbit\">\n" +
//        "</form>");
}
