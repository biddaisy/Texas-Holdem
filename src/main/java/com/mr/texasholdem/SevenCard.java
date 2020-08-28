package com.mr.texasholdem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SevenCard implements Comparable<SevenCard> {

  private final Card[] holeCards = new Card[2];

  private final CommunityCards communityCards;

  private Hand hand;

  public SevenCard(CommunityCards communityCards, String holeCardCodes) {
    if (holeCardCodes == null || holeCardCodes.length() != 4) {
      throw new IllegalArgumentException("Hole card code string length must be 4");
    }
    this.communityCards = communityCards;
    holeCards[0] = new Card(holeCardCodes.substring(0, 2));
    holeCards[1] = new Card(holeCardCodes.substring(2, 4));
    evaluate();
  }

  public int getHandValue() {
    return hand == null ? 0 : hand.getValue();
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

  private void evaluate() {
    List<Card> cards = getAllCardsAsList();
    boolean found = evaluateTwoPairs(new ArrayList<>(cards));
    if (found)
      return;
    found = evaluatePair(new ArrayList<>(cards));
    if (found)
      return;
    evaluateHighCard(cards);
  }

  private void evaluateHighCard(List<Card> cards) {
    hand = new HighCard(Collections.max(cards, new CardRankComparator()));
  }

  private boolean evaluateTwoPairs(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    int size = pairs.size();
    if (size < 2) {
      return false;
    }
    Collections.sort(pairs);
    hand = new TwoPairs(pairs.get(size - 1), pairs.get(size - 2));
    return true;
  }

  private boolean evaluatePair(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    Pair pair = !pairs.isEmpty() ? Collections.max(pairs) : null;
    return (hand = pair) != null;
  }

  private List<Pair> evaluatePairs(List<Card> cards, List<Pair> pairs) {
    int size = cards.size();
    Card card1 = cards.get(0);
    for (int a = 1; a < size; a++) {
      Card card2 = cards.get(a);
      if (card1.equalsByRank(card2)) {
        pairs.add(new Pair(card1, card2));
        cards.remove(card2);
        break;
      }
    }
    cards.remove(card1);
    if (cards.size() > 1) {
      return evaluatePairs(cards, pairs);
    }
    return pairs;
  }

}
