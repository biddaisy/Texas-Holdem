package com.mr.texasholdem;

public class HighCard extends Hand{

  private Card card;

  public HighCard(Card card) {
    super(1);
    this.card = card;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
