package com.mr.texasholdem;

import com.mr.texasholdem.card.Card;

import java.util.Arrays;

public class CommunityCards {

  private final Card[] cards = new Card[5];

  public CommunityCards(String cardCodes) throws WrongInputParameterException {
    if (cardCodes == null || cardCodes.length() != 10) {
      throw new WrongInputParameterException("Wrong community card code string length. It must be 10: " + cardCodes);
    }
    for (int a = 0; a < 10; a += 2) {
      Card card = Card.valueOf(cardCodes.substring(a, a + 2));
      Card.validateCard(cards, card);
      cards[a / 2] = card;
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
