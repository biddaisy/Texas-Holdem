package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class Flush extends Hand {

  private final Card[] cards;

  public Flush(Card[] cards) {
    super(FLUSH_VALUE, sortHandByRank(cards)[4].getRank());
    if (!isValidFlush(cards)){
      throw new IllegalArgumentException("wrong flush : " + Arrays.toString(cards));
    }
    this.cards = Arrays.copyOf(cards, 5);
  }

  public Card[] getCards() {
    return Arrays.copyOf(cards, 5);
  }

  public static boolean isValidFlush(Card[] five) {
    for (int a = 1; a < 5; a++) {
      if (!five[0].equalsBySuit(five[a])) {
        return false;
      }
    }
    return true;
  }
}
