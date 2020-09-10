package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class HighCard extends Hand {

  private final Card card;
  public HighCard(Card card, Card[] kickers) {
    super(HIGH_CARD_VALUE, card.getRank(), kickers);
    this.card = card;
  }

  public Card getCard() {
    return card;
  }

}
