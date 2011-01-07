package com.appspot.evetool.server.model;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 8, 2010
 * Time: 5:09:09 PM
 */
@PersistenceCapable
public class Ship {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent String gameId;
  @Persistent String name;
  @Persistent String type;
  @Persistent String race;
  @Persistent Blob icon;

  public Ship(String gameId, String name, String type, String race, Blob icon) {
    this.gameId = gameId;
    this.name = name;
    this.type = type;
    this.race = race;
    this.icon = icon;
  }

  public Key getKey() {
    return key;
  }

  public void setKey(Key key) {
    this.key = key;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }

  public Blob getIcon() {
    return icon;
  }

  public void setIcon(Blob icon) {
    this.icon = icon;
  }
}
