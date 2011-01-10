package com.appspot.evetool.server.model;

import com.appspot.evetool.server.factory.SF;
import com.appspot.evetool.server.service.EveCentralService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/9/11
 * Time: 3:18 PM
 */
public class Order {
  private String id;
  private String stationName;
  private String security;
  private String range;
  private String price;
  private String volRemain;
  private String minVolume;
  private String expires;
  private String reportedTime;
  private String type;

  public static List<Order> findOrdersByType(String typeId) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
    EveCentralService eveCentralService = SF.getEveCentralService();
    List<Order> orders = eveCentralService.fetchQuickLook(typeId);
    return orders;
  }

  public static Order findOrder(String id) {
    return null;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public Integer getVersion() {
    return 0;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getStationName() {
    return stationName;
  }

  public void setSecurity(String security) {
    this.security = security;
  }

  public String getSecurity() {
    return security;
  }

  public void setRange(String range) {
    this.range = range;
  }

  public String getRange() {
    return range;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPrice() {
    return price;
  }

  public void setVolRemain(String volRemain) {
    this.volRemain = volRemain;
  }

  public String getVolRemain() {
    return volRemain;
  }

  public void setMinVolume(String minVolume) {
    this.minVolume = minVolume;
  }

  public String getMinVolume() {
    return minVolume;
  }

  public void setExpires(String expires) {
    this.expires = expires;
  }

  public String getExpires() {
    return expires;
  }

  public void setReportedTime(String reportedTime) {
    this.reportedTime = reportedTime;
  }

  public String getReportedTime() {
    return reportedTime;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
