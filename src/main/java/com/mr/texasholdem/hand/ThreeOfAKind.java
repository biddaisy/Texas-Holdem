package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class ThreeOfAKind extends Pair {
  private final Card card3;

  public ThreeOfAKind(Card card1, Card card2, Card card3) {
    this(THREE_OF_A_KIND_VALUE, card1, card2, card3);
  }

  protected ThreeOfAKind(int handValue, Card card1, Card card2, Card card3) {
    super(handValue, card1, card2);
    if (!card3.getRank().equals(super.getRank())) {
      throw new IllegalArgumentException("Card ranks must be identical: " + card3 + " but rank is " + super.getRank());
    }
    this.card3 = card3;
  }

  public Card getCard3() {
    return card3;
  }

}
