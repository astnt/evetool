package com.appspot.evetool.client.factory;

import com.appspot.evetool.client.place.NavigationPlace;

/**
 * Created by IntelliJ IDEA.
 * User: ast
 * Date: 1/7/11
 * Time: 3:42 PM
 */
public class TokenizerFactory {
  public NavigationPlace.Tokenizer getNavigationTokenizer() {
    return new NavigationPlace.Tokenizer();
  }
}
