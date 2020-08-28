package com.mr.texasholdem;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    hand = new Evaluator().evaluate(getAllCardsAsList());
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

  private List<Card> getAllCardsAsList() {
    return Stream.concat(Arrays.stream(holeCards), Arrays.stream(communityCards.getCards())).collect(Collectors.toList());
  }

}
