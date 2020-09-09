package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class Straight extends Hand {

  private final Card[] cards;

  public Straight(Card[] cards) {
    this(STRAIGHT_VALUE, cards);
  }

  protected Straight(int handValue, Card[] cards) {
    super(handValue, sortHandByRank(cards)[4].getRank(), new Card[0]);
    if (!isValidStraight(cards)) {
      throw new IllegalArgumentException("wrong straight : " + Arrays.toString(cards));
    }
    this.cards = Arrays.copyOf(cards, 5);
  }

  public static boolean isValidStraight(Card[] five) {
    for (int a = 0; a < 4; a++) {
      if (!five[a].isFollowedBy(five[a + 1])) {
        return false;
      }
    }
    return true;
  }

  public Card[] getCards() {
    return Arrays.copyOf(cards, 5);
  }

  public boolean isFlush() {
    return Flush.isValidFlush(cards);
  }
}
