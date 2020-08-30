package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class StraightFlush extends Straight {

  public StraightFlush(Card[] cards) {
    super(STRAIGHT_FLUSH_VALUE, cards);
    if (!Flush.isValidFlush(getCards())) {
      throw new IllegalArgumentException("wrong flush: " + Arrays.toString(cards));
    }
  }
}
