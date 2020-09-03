package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class StraightFlush extends Straight {

  public StraightFlush(Card[] cards) {
    this(STRAIGHT_FLUSH_VALUE, cards);
  }

  protected StraightFlush(int handValue, Card[] cards) {
    super(handValue, cards);
    if (!Flush.isValidFlush(getCards())) {
      throw new IllegalArgumentException("wrong straight flush: " + Arrays.toString(cards));
    }
  }

  public boolean isRoyal() {
    return RoyalFlush.isValidRoyalFlush(getCards());
  }
}
