package com.appspot.evetool.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 5:51 PM
 */
public class NavigationPlace extends Place {
  private String race;
  private String ship;

  public NavigationPlace(String race, String ship) {
    this.race = race;
    this.ship = ship;
  }

  @Prefix("ship")
  public static class Tokenizer implements PlaceTokenizer<NavigationPlace> {
    @Override
    public String getToken(NavigationPlace place) {
      String race = place.getRace();
      String ship = place.getShip();
      boolean hasRace = race != null && race.length() > 0;
      boolean hasShip = ship != null && ship.length() > 0;
      if (hasRace && hasShip) { return race + "/" + ship; }
      else if (hasRace) { return race; }
      return "";
    }

    @Override
    public NavigationPlace getPlace(String token) {
      if (token == null || token.length() == 0) { return new NavigationPlace(null, null); }
      if (token.contains("/")) {
        String[] strings = token.split("/");
        return new NavigationPlace(strings[0], strings[1]);
      } else {
        return new NavigationPlace(token, null);
      }
    }
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }

  public String getShip() {
    return ship;
  }

  public void setShip(String ship) {
    this.ship = ship;
  }
}
