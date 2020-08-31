package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class Pair extends Hand {

  private final Card card1;

  private final Card card2;

  public Pair(Card card1, Card card2) {
    this(PAIR_VALUE, card1, card2);
  }

  protected Pair(int handValue, Card card1, Card card2) {
    super(handValue, card1.getRank());
    if (!card1.equalsByRank(card2)){
      throw new IllegalArgumentException("Card ranks must be identical: " + card1 + " and " + card2);
    }
    this.card1 = card1;
    this.card2 = card2;
  }

  public Card getCard1() {
    return card1;
  }

  public Card getCard2() {
    return card2;
  }

}
