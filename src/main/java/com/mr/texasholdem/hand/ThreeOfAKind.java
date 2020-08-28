package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class ThreeOfAKind extends Pair{
  private final Card card3;

  public ThreeOfAKind(Card card1, Card card2, Card card3) {
    super(card1, card2);
    if (!card3.getRank().equals(super.getRank())){
      throw new IllegalArgumentException("Card ranks must be identical");
    }
    this.card3 = card3;
  }

  public Card getCard3() {
    return card3;
  }
}
