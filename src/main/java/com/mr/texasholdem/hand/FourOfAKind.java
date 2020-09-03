package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class FourOfAKind extends ThreeOfAKind {

  private final Card card4;

  public FourOfAKind(Card card1, Card card2, Card card3, Card card4) {
    super(FOUR_OF_A_KIND_VALUE, card1, card2, card3);
    if (!card4.getRank().equals(super.getRank())) {
      throw new IllegalArgumentException("Card ranks must be identical: " + card4 + " but rank is " + super.getRank());
    }
    this.card4 = card4;
  }

  public Card getCard4() {
    return card4;
  }
}
