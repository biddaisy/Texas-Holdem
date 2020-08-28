package com.mr.texasholdem.card;

import java.util.Comparator;

public class CardRankComparator implements Comparator<Card> {
  @Override
  public int compare(Card card1, Card card2) {
    return card1.getRank().compareTo(card2.getRank());
  }
}
