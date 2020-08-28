package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;

public class HighCard extends Hand {

  private Card card;

  public HighCard(Card card) {
    super(1, card.getRank());
    this.card = card;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}