package com.appspot.evetool.client.proxy;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 12, 2010
 * Time: 11:10:49 AM
 */
public class ShipProxy implements Serializable {
  private String name;
  private String gameId;

  public ShipProxy() {
  }

  public ShipProxy(String gameId, String name) {
    this.gameId = gameId;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}
