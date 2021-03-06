package com.appspot.evetool.server.dao;

import com.appspot.evetool.server.model.Ship;
import com.google.inject.Singleton;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: Nov 11, 2010
 * Time: 3:18:47 PM
 * @deprecated
 */
@Singleton
public class ShipDao {
  public Ship getShipByGameId(PersistenceManager pm, String gameId) {
    Query query = pm.newQuery(Ship.class);
    query.setFilter("gameId == gameIdParam");
    query.declareParameters("String gameIdParam");

    try {
      List<Ship> results = (List<Ship>) query.execute(gameId);
      if (results.iterator().hasNext()) {
        return results.get(0);
      } else {
        return null;
      }
    } finally {
      query.closeAll();
    }
  }
  public List<Ship> getShipByRace(PersistenceManager pm, String race) {
    Query query = pm.newQuery(Ship.class);
    query.setFilter("race == raceParam");
    query.declareParameters("String raceParam");

    try {
      return (List<Ship>) query.execute(race);
    } finally {
      query.closeAll();
    }
  }

  public List<Ship> getAll(PersistenceManager pm) {
    Query query = pm.newQuery(Ship.class);
    try {
      return (List<Ship>) query.execute();
    } finally {
      query.closeAll();
    }
  }
}
