package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class Straight extends Hand {

  private final Card[] cards;

  public Straight(Card[] cards) {
    super(STRAIGHT_VALUE, sortHandByRank(cards)[4].getRank());
    if (!isValidStraight(cards)){
      throw new IllegalArgumentException("wrong straight : " + Arrays.toString(cards));
    }
    this.cards = Arrays.copyOf(cards, 5);
  }

  protected Straight(int handValue, Card[] cards) {
    super(handValue, sortHandByRank(cards)[4].getRank());
    if (!isValidStraight(cards)){
      throw new IllegalArgumentException("wrong straight : " + Arrays.toString(cards));
    }
    this.cards = Arrays.copyOf(cards, 5);
  }

  public Card[] getCards() {
    return Arrays.copyOf(cards, 5);
  }

  public boolean isFlush(){
    return Flush.isValidFlush(cards);
  }

  public static boolean isValidStraight(Card[] five) {
    for (int a = 0; a < 4; a++) {
      if (!five[a].isFollowedBy(five[a + 1])) {
        return false;
      }
    }
    return true;
  }
}
