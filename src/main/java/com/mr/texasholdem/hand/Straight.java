package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;

public class Straight extends Hand {

  private final Card[] cards;

  public Straight(Card[] cards) {
    super(STRAIGHT_VALUE, validateCards(cards)[0].getRank());
    this.cards = Arrays.copyOf(cards, 5);
  }

  public Card[] getCards() {
    return Arrays.copyOf(cards, 5);
  }

  public boolean hasAce(){
    return Card.hasAce(cards);
  }

  private static Card[] validateCards(Card[] cards) {
    if (cards == null || cards.length != 5) {
      throw new IllegalArgumentException("Straight must have 5 cards");
    }
    return cards;
  }
}
