package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.Rank;

public class RoyalFlush extends StraightFlush {

  public RoyalFlush(Card[] cards) {
    super(ROYAL_FLUSH_VALUE, cards);
    Card[] straightFlushCards = getCards();
    if (!isValidRoyalFlush(straightFlushCards)) {
      throw new IllegalArgumentException("wrong royal flush: " + Arrays.toString(cards));
    }
  }

  public static boolean isValidRoyalFlush(Card[] straightFlushCards) {
    return straightFlushCards[0].getRank() == Rank.TEN;
  }
}
