package com.appspot.evetool.server.model;

import com.appspot.evetool.server.factory.DB;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.List;

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
  @Persistent Text description;
  @Persistent Blob icon;
  @Persistent private Blob img256;

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

  public String getId() {
    return gameId;
  }

  public Integer getVersion() {
    return 1;
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

  public Blob getImg256() {
    return img256;
  }

  public void setImg256(Blob img256) {
    this.img256 = img256;
  }

  public String getDescription() {
    return description.getValue();
  }

  public void setDescription(String description) {
    this.description = new Text(description);
  }

  public static List<Ship> findAllShips() {
    PersistenceManager pm = DB.getManager();
    Query query = pm.newQuery(Ship.class);
    List<Ship> ships = (List<Ship>) query.execute();
    pm.retrieveAll(ships);
    return ships;
  }

  public static Ship findShip(String id) {
    PersistenceManager pm = DB.getManager();
    Query query = pm.newQuery(Ship.class);
    query.setFilter("gameId == gameIdParam");
    query.declareParameters("String gameIdParam");
    List<Ship> results = (List<Ship>) query.execute(id);
    if (results.iterator().hasNext()) {
      Ship ship = results.get(0);
      pm.retrieve(ship);
      return ship;
    } else {
      return null;
    }
  }

  public static Ship findShipByName(String name) {
    PersistenceManager pm = DB.getManager();
    Query query = pm.newQuery(Ship.class);
    query.setFilter("name == nameParam");
    query.declareParameters("String nameParam");
    List<Ship> ships = (List<Ship>) query.execute(name);
    if (ships.isEmpty()) { return null; }
    return ships.get(0);
  }

  public void persist() {
  }

  public void remove() {
  }
}
