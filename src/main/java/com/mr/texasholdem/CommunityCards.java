package com.mr.texasholdem;

import com.mr.texasholdem.card.Card;

import java.util.Arrays;

public class CommunityCards {

  private final Card[] cards = new Card[5];

  public CommunityCards(String cardCodes) {
    if (cardCodes == null || cardCodes.length() != 10) {
      throw new IllegalArgumentException("Wrong community Card code string length. It must be 10");
    }
    for (int a = 0; a < 10; a += 2) {
      cards[a / 2] = Card.valueOf(cardCodes.substring(a, a + 2));
    }
  }

  public Card[] getCards() {
    return Arrays.copyOf(cards, 5);
  }

  @Override
  public String toString() {
    return Arrays.stream(cards).reduce("", (c1, c2) -> c1.concat(c2.toString()), String::concat);
  }
}
