package com.appspot.evetool.server.service;

import com.appspot.evetool.server.model.Order;
import com.google.inject.Singleton;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/9/11
 * Time: 3:10 PM
 */
@Singleton
public class EveCentralService {
  private static final Logger log = Logger.getLogger(EveCentralService.class.getName());

  public List<Order> fetchQuickLook(String typeId) throws IOException, SAXException, ParserConfigurationException,
      XPathExpressionException {
    List<Order> orders = new ArrayList<Order>();
    Document doc = getXml("http://api.eve-central.com/api/quicklook?typeid=" + typeId);
    xpath(doc, "");
    parseOrders(orders, doc, "sell_orders", "evec_api/quicklook/sell_orders/order");
    parseOrders(orders, doc, "buy_orders", "evec_api/quicklook/buy_orders/order");
    return orders;
  }

  private void parseOrders(List<Order> orders, Document doc, String type, String path) throws XPathExpressionException {
    NodeList nodes = xpath(doc, path);
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      Order order = new Order();
      order.setType(type);
      order.setId(node.getAttributes().getNamedItem("id").getNodeValue());
      NodeList childNodes = node.getChildNodes();
      for (int j = 0; j < childNodes.getLength(); j++) {
        Node child = childNodes.item(j);
        if (child.getNodeType() == Document.ELEMENT_NODE) {
          insertValues(order, child);
        }
      }
      orders.add(order);
    }
  }

  private void insertValues(Order order, Node child) {
    if ("station_name".equals(child.getNodeName())) {
      order.setStationName(child.getTextContent());
    } else if ("security".equals(child.getNodeName())) {
      order.setSecurity(child.getTextContent());
    } else if ("range".equals(child.getNodeName())) {
      order.setRange(child.getTextContent());
    } else if ("price".equals(child.getNodeName())) {
      order.setPrice(child.getTextContent());
    } else if ("vol_remain".equals(child.getNodeName())) {
      order.setVolRemain(child.getTextContent());
    } else if ("min_volume".equals(child.getNodeName())) {
      order.setMinVolume(child.getTextContent());
    } else if ("expires".equals(child.getNodeName())) {
      order.setExpires(child.getTextContent());
    } else if ("reported_time".equals(child.getNodeName())) {
      order.setReportedTime(child.getTextContent());
    }
  }

  private Document getXml(String url) throws IOException, SAXException, ParserConfigurationException {
    URL address = new URL(url);
    URLConnection connection = address.openConnection();
    connection.setReadTimeout(10000);
    connection.setConnectTimeout(10000);
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder xml = new StringBuilder();
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      xml.append(inputLine);
    }
    in.close();
    return read(xml.toString());
  }

  private Document read(String xml) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(xml);
  }

  private NodeList xpath(Document doc, String path) throws XPathExpressionException {
    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile(path);
    return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
  }
}
