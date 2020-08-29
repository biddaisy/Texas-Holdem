package com.mr.texasholdem;

import java.util.Objects;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;

public class SevenCard implements Comparable<SevenCard> {

  private final Card[] holeCards = new Card[2];

  private final CommunityCards communityCards;

  private final Hand hand;

  public SevenCard(CommunityCards communityCards, String holeCardCodes) {
    if (holeCardCodes == null || holeCardCodes.length() != 4) {
      throw new IllegalArgumentException("Hole Card code string length must be 4");
    }
    this.communityCards = communityCards;
    holeCards[0] = new Card(holeCardCodes.substring(0, 2));
    holeCards[1] = new Card(holeCardCodes.substring(2, 4));
    hand = new Evaluator().evaluate(getAllCards());
  }

  public Hand getHand() {
    return hand;
  }

  public String getHoleCardCodes() {
    return holeCards[0].toString() + holeCards[1].toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    SevenCard sevenCard = (SevenCard) o;
    return hand.equals(sevenCard.hand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hand);
  }

  @Override
  public int compareTo(SevenCard o) {
    return hand.compareTo(o.hand);
  }

  private Card[] getAllCards() {
    Card[] cards = new Card[7];
    System.arraycopy(communityCards.getCards(), 0, cards, 0, 5);
    System.arraycopy(holeCards, 0, cards, 5, 2);
    return cards;
  }
}
